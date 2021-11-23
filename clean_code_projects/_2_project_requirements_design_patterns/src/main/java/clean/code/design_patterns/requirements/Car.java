package clean.code.design_patterns.requirements;

public class Car {
    private final Object carManufacturer;

    private enum carManufacturer{
        BMW, Audi, Deimler, Ford, Porsche, Lamborghini
    }
    private String carModel;
    private int numberOfWheels;
    private int numberOfSeats;
    private String engineModel;
    private String carColor;
    private boolean navigation;
    private boolean bluetooth;
    private boolean adaptiveCruiseControl;

    public Object getCarManufacturer() { return carManufacturer; }
    public String getCarModel() { return carModel; }
    public int getNumberOfWheels() { return numberOfWheels; }
    public int getNumberOfSeats() { return numberOfSeats; }

    public String withEngineModel() { return engineModel;}
    public String withCarColor() { return carColor;}
    public boolean withNavigation() { return navigation;}
    public boolean withBluetooth() { return bluetooth;}
    public boolean withAdaptiveCruiseControl() { return adaptiveCruiseControl;}

    private Car(Builder builder){
        this.carManufacturer = builder.carManufacturer;
        this.carModel = builder.carModel;
        this.numberOfWheels = builder.numberOfWheels;
        this.numberOfSeats = builder.numberOfSeats;
        this.engineModel = builder.engineModel;
        this.carColor = builder.carColor;
        this.navigation = builder.navigation;
        this.bluetooth = builder.bluetooth;
        this.adaptiveCruiseControl = builder.adaptiveCruiseControl;
    }

    public static class Builder{
        private final Object carManufacturer;

        private enum carManufacturer{
            BMW, Audi, Deimler, Ford, Porsche, Lamborghini
        }
        private String carModel;
        private int numberOfWheels;
        private int numberOfSeats;

        private String engineModel;
        private String carColor;
        private boolean navigation;
        private boolean bluetooth;
        private boolean adaptiveCruiseControl;

        public Builder(String carManufacturer,String carModel,int numberOfWheels,int numberOfSeats){
            this.carManufacturer = carManufacturer;
            this.carModel = carModel;
            if(numberOfWheels != 2 && numberOfWheels != 3  && numberOfWheels != 4 && numberOfSeats != 2
               && numberOfSeats != 3 && numberOfSeats != 4 && numberOfSeats != 8){
                this.numberOfWheels = 4;
                this.numberOfSeats = 4;
            }
            else{
                this.numberOfWheels = numberOfWheels;
                this.numberOfSeats = numberOfSeats;
            }
        }

        public Builder withEngineModel(String engineModel){
            this.engineModel = engineModel;
            return this;
        }

        public Builder withCarColor(String carColor){
            this.carColor = carColor;
            return this;
        }

        public Builder withNavigation(boolean navigation){
            this.navigation = navigation;
            return this;
        }

        public Builder withBluetooth(boolean bluetooth){
            this.bluetooth = bluetooth;
            return this;
        }

        public Builder withAdaptiveCruiseControl(boolean adaptiveCruiseControl){
            this.adaptiveCruiseControl = adaptiveCruiseControl;
            return this;
        }

        public Object getCarManufacturer() { return carManufacturer; }
        public String getCarModel() { return carModel; }
        public int getNumberOfWheels() { return numberOfWheels; }
        public int getNumberOfSeats() { return numberOfSeats; }
        public String getEngineModel() { return engineModel; }
        public String getCarColor() { return carColor; }
        public boolean isNavigation() { return navigation; }
        public boolean isBluetooth() { return bluetooth; }
        public boolean isAdaptiveCruiseControl() { return adaptiveCruiseControl; }

        public Car buildCar(){
            return new Car(this);
        }
    }
}


