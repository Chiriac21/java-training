public class Fighter {
    public String name;
    public int health;
    public double damagePerAttack;

    public Fighter(String name, int health, double damagePerAttack) {
        this.name = name;
        if(health > 100 || health <= 0) {
            this.health = 100;
        }else{
            this.health = health;
        }
        this.damagePerAttack = damagePerAttack;
    }

    public void attack(Fighter opponent){
        opponent.health -= this.damagePerAttack;
    }
}
