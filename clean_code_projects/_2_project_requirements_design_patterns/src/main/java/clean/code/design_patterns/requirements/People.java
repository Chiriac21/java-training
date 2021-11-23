package clean.code.design_patterns.requirements;

import java.util.Random;

class People {
    private int peopleAtShow;
    public People(){
        Random r = new Random();
        int randomNumberOfPeople = (int)r.nextInt(50000);
        this.peopleAtShow = randomNumberOfPeople;
    }

    public int getPeopleAtShow(){return peopleAtShow;}
    public void setPeople(int people){
        this.peopleAtShow = people;
    }
}
