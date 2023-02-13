package com.pointOfSale.Keels.pointofsale.controller;

import com.pointOfSale.Keels.pointofsale.dto.ItemDTO;
import com.pointOfSale.Keels.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.ItemSaveRequestDTO;
import com.pointOfSale.Keels.pointofsale.dto.request.ItemUpdateRequestDTO;
import com.pointOfSale.Keels.pointofsale.service.ItemService;
import com.pointOfSale.Keels.pointofsale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(
            path = {"/item-save"}
    )
    public ResponseEntity<StandardResponse> itemSave(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
        String id = itemService.saveItems(itemSaveRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Ok", id)
                , HttpStatus.CREATED
        );
    }

    @GetMapping(
            path = {"/get-element-by-id"},
            params = {"id"}
    )
    public ResponseEntity<StandardResponse> getItemById(@RequestParam(value = "id") int id) {
        ItemDTO itemDTO = itemService.getItemById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", itemDTO), HttpStatus.OK
        );
    }

    @GetMapping(
            path = {"/get-all-items"}
    )
    public ResponseEntity<StandardResponse> getAllItems() {
        List<ItemDTO> items = itemService.getAllItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "success", items), HttpStatus.OK
        );
    }

    @GetMapping(
            path = {"/get-all-items-by-state"},
            params = {"state"}
    )
    public ResponseEntity<StandardResponse> getItemsByState(@RequestParam(value = "state") String state) {
        if (state.equalsIgnoreCase("active") | state.equalsIgnoreCase("inactive")) {
            boolean status = state.equalsIgnoreCase("active") ? true : false;
            List<ItemDTO> stateData = itemService.getItemsByState(status);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Success", stateData), HttpStatus.OK
            );
        } else {
            List<ItemDTO> stateData = itemService.getAllItems();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Success", stateData), HttpStatus.OK
            );
        }
    }

    @PutMapping(
            path = {"/upate-item-by-id"},
            params = {"itemId"}
    )
    public ResponseEntity<StandardResponse> updateItemById(@RequestBody ItemUpdateRequestDTO itemUpdateRequestDTO, @RequestParam(value = "itemId") int itemId) {
        String itemUpdated = itemService.itemUpdate(itemUpdateRequestDTO, itemId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", itemUpdated), HttpStatus.OK
        );
    }

    @GetMapping(
            path = {"/get-item-count-by-state"},
            params = {"state"}
    )
    public ResponseEntity<StandardResponse> getCountByState(@RequestParam(value = "state") boolean state) {
        long itemCount;
        if (state == true) {
            itemCount = itemService.getItemCountbyStateTrue(state);
        } else {
            itemCount = itemService.getItemCountbyStateFalse(state);
        }
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Ok", state+" count : "+itemCount), HttpStatus.OK
        );
    }

    @GetMapping(
            path = {"/get-All-item-counts"}
    )
    public ResponseEntity<StandardResponse> getAllItemCount(){
        long allItemCounts = itemService.getAllCountItem();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"OK",allItemCounts),HttpStatus.OK
        );
    }

    @GetMapping(
            path = {"/get-all-item-paginated"},
            params = {"page","size"}
    )
    public ResponseEntity<StandardResponse> getAllItemsPaginated(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size){

        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItemPaginatedData(page,size);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Ok",paginatedResponseItemDTO),HttpStatus.OK
        );
    }

    @GetMapping(
            path = {"/get-all-active-state-paginated"},
            params = {"page","size","activeState"}
    )
    public ResponseEntity<StandardResponse> getAllAvtiveItemsPaginated(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(30) int size,
            @RequestParam(value = "activeState") boolean activeState
    ){
        PaginatedResponseItemDTO paginatedResponseItemDTOs = itemService.getAllActiveItemPaginatedData(page,size,activeState);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"OK",paginatedResponseItemDTOs ),HttpStatus.OK
        );
    }

}
