package com.zaptech.json.NewsItem_model;

public class Items_Model {

	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
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

	public String getEventFlag() {
		return eventFlag;
	}

	public void setEventFlag(String eventFlag) {
		this.eventFlag = eventFlag;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getPublishToFacebook() {
		return publishToFacebook;
	}

	public void setPublishToFacebook(String publishToFacebook) {
		this.publishToFacebook = publishToFacebook;
	}

	public String getTempUniqueUID() {
		return tempUniqueUID;
	}

	public void setTempUniqueUID(String tempUniqueUID) {
		this.tempUniqueUID = tempUniqueUID;
	}

	public String getEventDateFinish() {
		return eventDateFinish;
	}

	public void setEventDateFinish(String eventDateFinish) {
		this.eventDateFinish = eventDateFinish;
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

	public NewsImage_Model getObj_newsImage() {
		return obj_newsImage;
	}

	public void setObj_newsImage(NewsImage_Model obj_newsImage) {
		this.obj_newsImage = obj_newsImage;
	}

	public Headline_Model getObj_headline() {
		return obj_headline;
	}

	public void setObj_headline(Headline_Model obj_headline) {
		this.obj_headline = obj_headline;
	}

	public Description_Model getObj_description() {
		return obj_description;
	}

	public void setObj_description(Description_Model obj_description) {
		this.obj_description = obj_description;
	}

	public DescriptionHTML_Model getObj_descriptionHTML() {
		return obj_descriptionHTML;
	}

	public void setObj_descriptionHTML(DescriptionHTML_Model obj_descriptionHTML) {
		this.obj_descriptionHTML = obj_descriptionHTML;
	}

	String Url;
	String datePublished;
	String dateChanged;
	String isDirty;
	String eventFlag;
	String eventDate;
	String publishToFacebook;
	String tempUniqueUID;
	String eventDateFinish;
	String sortPosition;
	String archived;
	String listIcon;

	NewsImage_Model obj_newsImage;
	Headline_Model obj_headline ;
	Description_Model obj_description ;
	DescriptionHTML_Model obj_descriptionHTML ;

}
