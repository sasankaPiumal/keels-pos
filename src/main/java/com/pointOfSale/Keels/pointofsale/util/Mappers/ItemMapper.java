package com.pointOfSale.Keels.pointofsale.util.Mappers;

import com.pointOfSale.Keels.pointofsale.dto.ItemDTO;
import com.pointOfSale.Keels.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.ItemSaveRequestDTO;
import com.pointOfSale.Keels.pointofsale.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {


    ItemDTO itemEntityToDto(Item item);

    List<ItemDTO> entityToDtos(List<Item> items);

    Item RequestDtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemDTO>  pageToList(Page<Item> page);
}
