public interface HealthPoint {
    public int getHP();

    public void setHP(int healthPoint);

    public boolean isDead();

    public boolean hasCollision(int x, int y);
}
