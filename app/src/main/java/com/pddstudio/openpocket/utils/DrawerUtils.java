package com.pddstudio.openpocket.utils;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.pddstudio.openpocket.R;
import com.pddstudio.pocketlibrary.OpenPocket;
import com.pddstudio.pocketlibrary.models.Category;
import com.pddstudio.pocketutils.IconParser;

import java.util.LinkedList;
import java.util.List;

/**
 * This Class was created by Patrick J
 * on 18.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public final class DrawerUtils {

    public static List<IDrawerItem> getDrawerItems() {
        List<IDrawerItem> drawerItems = new LinkedList<>();
        SectionDrawerItem generalSection = new SectionDrawerItem().withDivider(false).withName(R.string.drawer_category_gen);
        drawerItems.add(generalSection);
        PrimaryDrawerItem homeItem = new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(CommunityMaterial.Icon.cmd_home);
        drawerItems.add(homeItem);
        SectionDrawerItem categorySection = new SectionDrawerItem().withName(R.string.drawer_category_cat);
        drawerItems.add(categorySection);
        for(Category category : OpenPocket.get().getCategoryManager().getCategoryList()) {
            PrimaryDrawerItem catItem = new PrimaryDrawerItem().withName(category.getCategoryName()).withDescription(category.getCategoryDescription()).withIcon(IconParser.getIcon(category.getCategoryIcon()));
            drawerItems.add(catItem);
        }
        DividerDrawerItem dividerItem = new DividerDrawerItem();
        SectionDrawerItem prefSection = new SectionDrawerItem().withDivider(false).withName(R.string.drawer_category_pref);
        PrimaryDrawerItem aboutItem = new PrimaryDrawerItem().withName(R.string.drawer_item_about).withIcon(CommunityMaterial.Icon.cmd_information_outline);
        PrimaryDrawerItem prefItem = new PrimaryDrawerItem().withName(R.string.drawer_item_pref).withIcon(CommunityMaterial.Icon.cmd_settings);
        drawerItems.add(dividerItem);
        drawerItems.add(prefSection);
        drawerItems.add(aboutItem);
        drawerItems.add(prefItem);
        return drawerItems;
    }

}
