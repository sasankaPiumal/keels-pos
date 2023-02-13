package com.pointOfSale.Keels.pointofsale.service;

import com.pointOfSale.Keels.pointofsale.dto.ItemDTO;
import com.pointOfSale.Keels.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.ItemSaveRequestDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.ItemUpdateRequestDTO;

import java.util.List;

public interface ItemService {
    String saveItems(ItemSaveRequestDTO itemSaveRequestDTO);

    ItemDTO getItemById(int id);

    List<ItemDTO> getAllItems();

    List<ItemDTO> getItemsByState(boolean status);

    String itemUpdate(ItemUpdateRequestDTO itemUpdateRequestDTO, int itemId);

    long getItemCountbyStateTrue(boolean state);

    long getItemCountbyStateFalse(boolean state);

    long getAllCountItem();

    PaginatedResponseItemDTO getAllItemPaginatedData(int page, int size);

    PaginatedResponseItemDTO getAllActiveItemPaginatedData(int page, int size, boolean activeState);
}
