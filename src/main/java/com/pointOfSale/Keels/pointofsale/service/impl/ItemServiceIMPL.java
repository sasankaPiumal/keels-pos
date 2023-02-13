package com.pointOfSale.Keels.pointofsale.service.impl;

import com.pointOfSale.Keels.pointofsale.dto.CustomerDTO;
import com.pointOfSale.Keels.pointofsale.dto.ItemDTO;
import com.pointOfSale.Keels.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.ItemSaveRequestDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.ItemUpdateRequestDTO;
import com.pointOfSale.Keels.pointofsale.entity.Item;
import com.pointOfSale.Keels.pointofsale.entity.enums.MeasuringUnitType;
import com.pointOfSale.Keels.pointofsale.exception.NotFoundException;
import com.pointOfSale.Keels.pointofsale.repository.ItemRepository;
import com.pointOfSale.Keels.pointofsale.service.ItemService;
import com.pointOfSale.Keels.pointofsale.util.Mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String saveItems(ItemSaveRequestDTO itemSaveRequestDTO) {
//        Item item = new Item(
//                itemSaveRequestDTO.getItemName(),
//                itemSaveRequestDTO.getMeasuringUnit(),
//                itemSaveRequestDTO.getBalanceQty(),
//                itemSaveRequestDTO.getSupplierPrice(),
//                itemSaveRequestDTO.getSellingPrice()
//        );
//        if (!itemRepo.existsById(item.getItemId())){
//            //item.setActiveSatate(true);
////            itemRepo.save(item);
//            return itemRepo.save(item).getItemName()+" Item saved successfully. ";
//        }
//        else{
//            return item.getItemName()+"already exists.";
//        }
        Item item = itemMapper.RequestDtoToEntity(itemSaveRequestDTO);
        item.setActiveSatate(true);

        if (!itemRepo.existsById(item.getItemId())){
            return itemRepo.save(item).getItemName()+" saved.";
        }
        else{
            return item.getItemName()+" already exists in database.";
        }

    }

    @Override
    public ItemDTO getItemById(int id) {
        Optional<Item> item = itemRepo.findById(id);
        if (item.isPresent()){
//            ItemDTO itemDTO = new ItemDTO(
//                    item.get().getItemId(),
//                    item.get().getItemName(),
//                    item.get().getMeasuringUnit(),
//                    item.get().getBalanceQty(),
//                    item.get().getSupplierPrice(),
//                    item.get().getSellingPrice(),
//                    item.get().isActiveSatate()
//            );
            ItemDTO itemDTO = itemMapper.itemEntityToDto(item.get());
            return itemDTO;
        }

        return null;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepo.findAll();
//        List<ItemDTO> itemLists = new ArrayList<>();
//
//        for (Item i : itemDTOS ) {
//           ItemDTO itemDTO = new ItemDTO(
//                   i.getItemId(),
//                   i.getItemName(),
//                   i.getMeasuringUnit(),
//                   i.getBalanceQty(),
//                   i.getSupplierPrice(),
//                   i.getSellingPrice(),
//                   i.isActiveSatate()
//           );
//           itemLists.add(itemDTO);
//        }
//        return itemLists;
        if (!items.isEmpty()){
            List<ItemDTO> itemDTOs = itemMapper.entityToDtos(items);
            return itemDTOs;
        }
        else {
            throw new NotFoundException("Item does not exists.");
        }

    }

    @Override
    public List<ItemDTO> getItemsByState(boolean status) {
        List<Item> items = itemRepo.findAllByActiveSatateEquals(status);

        if (items.size() != 0){
            List<ItemDTO> itemDTOS = itemMapper.entityToDtos(items);
            return itemDTOS;
        }
        else {
           throw new NotFoundException("Not Found Data By "+status+" state");
        }

    }

    @Override
    public String itemUpdate(ItemUpdateRequestDTO itemUpdateRequestDTO, int itemId) {
        if (itemRepo.existsById(itemId)){
            Item item = itemRepo.getReferenceById(itemId);

            item.setItemName(itemUpdateRequestDTO.getItemName());
//            item.setMeasuringUnit(itemUpdateRequestDTO.getMeasuringUnit());
            item.setBalanceQty(itemUpdateRequestDTO.getBalanceQty());
            item.setSupplierPrice(itemUpdateRequestDTO.getSupplierPrice());
            item.setSellingPrice(itemUpdateRequestDTO.getSellingPrice());

            return itemRepo.save(item).getItemName()+" updated successfully.";
        }
        else {
            return "Not exist such item in the database.";
        }
    }

    @Override
    public long getItemCountbyStateTrue(boolean state) {
        long itemTrue = itemRepo.countAllByActiveSatateEquals(state);
        return itemTrue;
    }

    @Override
    public long getItemCountbyStateFalse(boolean state) {
        long itemTrue = itemRepo.countAllByActiveSatateEquals(state);
        return itemTrue;
    }

    @Override
    public long getAllCountItem() {
//        long countTrue = itemRepo.countAllByActiveSatateEquals(true);
//        long countFalse = itemRepo.countAllByActiveSatateEquals(false);
//        long countAlls = countTrue + countFalse;
        long countAlls = itemRepo.count();
        return countAlls;
    }

    @Override
    public PaginatedResponseItemDTO getAllItemPaginatedData(int page, int size) {

        Page<Item> getAllItemsByPaginated = itemRepo.findAll(PageRequest.of(page,size));

        return new PaginatedResponseItemDTO(
                itemMapper.pageToList(getAllItemsByPaginated),
                itemRepo.count()
        );
    }

    @Override
    public PaginatedResponseItemDTO getAllActiveItemPaginatedData(int page, int size, boolean activeState) {
        Page<Item> getAllActiveItemPaginated = itemRepo.findAllByActiveSatateEquals(activeState,PageRequest.of(page, size));
        return new PaginatedResponseItemDTO(
                itemMapper.pageToList(getAllActiveItemPaginated),
                itemRepo.countAllByActiveSatateEquals(activeState)
        );
    }


}
