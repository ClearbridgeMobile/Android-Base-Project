package com.clearbridgemobile.core.interfaces;

import com.clearbridgemobile.core.enums.PageId;
import com.google.j2objc.annotations.ObjectiveCName;

public interface AppNavigationInterface {
    @ObjectiveCName(value="goToPage:(PageIdEnum *)pageId")
    public void goToPage(PageId page);
}
