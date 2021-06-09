package com.oguzdirenc.notebook.service;

import com.oguzdirenc.notebook.domain.Item;
import com.oguzdirenc.notebook.request.ItemSaveRequest;

public interface ItemService {

    Item saveItem(ItemSaveRequest itemSaveRequest);

}
