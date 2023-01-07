
public class Dataset implements Comparable<Dataset> {

    private Integer number_of_item;
    private Integer item_id;
    private String name;
    private String category;
    private Double price;
    private String old_price;
    private Boolean sellable_online;
    private String link;
    private Boolean other_colors;
    private String short_description;
    private String designer;
    private Integer depth;
    private Integer height;
    private Integer width;

    public Dataset(Integer number_of_item, Integer item_id, String name, String category, Double price,
            String old_price,
            Boolean sellable_online, String link,
            Boolean other_colors, String short_description, String designer, Integer depth, Integer height,
            Integer width) {
        this.number_of_item = number_of_item;
        this.item_id = item_id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.old_price = old_price;
        this.sellable_online = sellable_online;
        this.link = link;
        this.other_colors = other_colors;
        this.short_description = short_description;
        this.designer = designer;
        this.depth = depth;
        this.height = height;
        this.width = width;

    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOld_price() {
        return old_price;
    }

    public void setOld_price(String old_price) {
        this.old_price = old_price;
    }

    public Boolean getSellable_online() {
        return sellable_online;
    }

    public void setSellable_online(Boolean sellable_online) {
        this.sellable_online = sellable_online;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getOther_colors() {
        return other_colors;
    }

    public void setOther_colors(Boolean other_colors) {
        this.other_colors = other_colors;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getNumber_of_item() {
        return number_of_item;
    }

    public void setNumber_of_item(Integer number_of_item) {
        this.number_of_item = number_of_item;
    }

    @Override
    public String toString() {
        return ("\n" + "Entity: " + this.getNumber_of_item() + "\n" +
                "Item Id: " + this.getItem_id() + "\n" +
                "Name: " + this.getName() + "\n" +
                "Category: " + this.getCategory() + "\n" +
                "Price: " + this.getPrice() + "\n" +
                "Old Price: " + this.getPrice() + "\n" +
                "Sellable Online: " + this.getSellable_online() + "\n" +
                "Link: " + this.getLink() + "\n" +
                "Other Colors: " + this.getOther_colors() + "\n" +
                "Short Description: " + this.getShort_description() + "\n" +
                "Designer: " + this.getDesigner() + "\n" +
                "Depth: " + this.getDepth() + "\n" +
                "Height: " + this.getHeight() + "\n" +
                "Width: " + this.getWidth() + "\n");
    }

    @Override
    public int compareTo(Dataset o) {
        // TODO Auto-generated method stub
        return 0;
    }

}