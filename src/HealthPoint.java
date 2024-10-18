public interface HealthPoint {
    public int getHP();

    public void setHP(int healthPoint);

    public boolean isAlive();

    public boolean hasCollision(int x, int y);
}
