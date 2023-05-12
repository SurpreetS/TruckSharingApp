package sqllitehelper;

public class MyOrderData {

    private int id;
    private String pickupTime;
    private String pickupDate;
    private String receiverName;
    private String pickupLocation;
    private String vehicleType;
    private String goodType;
    private double weight;
    private double length;
    private double height;
    private double width;
    private String description;
    private String truckName;
    private int image;

    public MyOrderData() {
    }

    public MyOrderData(String pickupTime, String pickupDate, String receiverName, String pickupLocation, String vehicleType, String goodType, double weight, double length, double height, double width, String description, String truckName, int image) {
        this.id = id;
        this.pickupTime = pickupTime;
        this.pickupDate = pickupDate;
        this.receiverName = receiverName;
        this.pickupLocation = pickupLocation;
        this.vehicleType = vehicleType;
        this.goodType = goodType;
        this.weight = weight;
        this.length = length;
        this.height = height;
        this.width = width;
        this.description = description;
        this.truckName = truckName;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getGoodType() {
        return goodType;
    }

    public double getWeight() {
        return weight;
    }

    public double getLength() {
        return length;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public String getDescription() {
        return description;
    }

    public String getTruckName() {
        return truckName;
    }

    public int getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }
}
