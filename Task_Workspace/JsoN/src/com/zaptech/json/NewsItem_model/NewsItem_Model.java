package com.zaptech.json.NewsItem_model;

import java.util.ArrayList;
import java.util.List;

public class NewsItem_Model {

	String videos;

	
	Items_Model obj_item;
	
	
	
	public Items_Model getObj_item() {
		return obj_item;
	}

	public void setObj_item(Items_Model obj_item) {
		this.obj_item = obj_item;
	}

	/*ArrayList<Items_Model> item_list;

	public ArrayList<Items_Model> getItem_list() {
		return item_list;
	}

	public void setItem_list(ArrayList<Items_Model> item_list) {
		this.item_list = item_list;
	}
*/
	public String getVideos() {
		return videos;
	}

	public void setVideos(String videos) {
		this.videos = videos;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getSharePointURL() {
		return SharePointURL;
	}

	public void setSharePointURL(String sharePointURL) {
		SharePointURL = sharePointURL;
	}

	public String getDisplayAsGantt() {
		return DisplayAsGantt;
	}

	public void setDisplayAsGantt(String displayAsGantt) {
		DisplayAsGantt = displayAsGantt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTabPosition() {
		return tabPosition;
	}

	public void setTabPosition(String tabPosition) {
		this.tabPosition = tabPosition;
	}

	public String getTabText() {
		return tabText;
	}

	public void setTabText(String tabText) {
		this.tabText = tabText;
	}

	public String getTabIcon() {
		return tabIcon;
	}

	public void setTabIcon(String tabIcon) {
		this.tabIcon = tabIcon;
	}

	public String getDateChanged() {
		return dateChanged;
	}

	public void setDateChanged(String dateChanged) {
		this.dateChanged = dateChanged;
	}

	public String getIsDirty() {
		return isDirty;
	}

	public void setIsDirty(String isDirty) {
		this.isDirty = isDirty;
	}

	public String getTempUniqueUID() {
		return tempUniqueUID;
	}

	public void setTempUniqueUID(String tempUniqueUID) {
		this.tempUniqueUID = tempUniqueUID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUseTabIcon() {
		return useTabIcon;
	}

	public void setUseTabIcon(String useTabIcon) {
		this.useTabIcon = useTabIcon;
	}

	public String getSortPosition() {
		return sortPosition;
	}

	public void setSortPosition(String sortPosition) {
		this.sortPosition = sortPosition;
	}

	public String getArchived() {
		return archived;
	}

	public void setArchived(String archived) {
		this.archived = archived;
	}

	public String getListIcon() {
		return listIcon;
	}

	public void setListIcon(String listIcon) {
		this.listIcon = listIcon;
	}

	String sortType;
	String SharePointURL;
	String DisplayAsGantt;
	String id;
	String tabPosition;
	String tabText;
	String tabIcon;
	String dateChanged;
	String isDirty;
	String tempUniqueUID;
	String type;
	String useTabIcon;
	String sortPosition;
	String archived;
	String listIcon;

}
