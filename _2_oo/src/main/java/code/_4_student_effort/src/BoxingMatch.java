public class BoxingMatch {
    private Fighter fighter1;
    private Fighter fighter2;

    public BoxingMatch(Fighter f1, Fighter f2) {
        this.fighter1 = f1;
        this.fighter2 = f2;
    }
    public String fight(){
        while(fighter1.health > 0 && fighter2.health > 0){
            fighter1.attack(fighter2);
            fighter2.attack(fighter1);
        }
        if(fighter1.health <= 0){
            return fighter2.name;
        }else{
            return fighter1.name;
        }
    }

    public static void main(String[] args) {
            Fighter f1 = new Fighter("Fighter 1", 100, 15);
            Fighter f2 = new Fighter("Fighter 2", 100, 30);

            BoxingMatch boxingMatch = new BoxingMatch(f1,f2);

        System.out.println("The winner is: " + boxingMatch.fight());
    }
}
