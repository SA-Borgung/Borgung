package ku.cs.services;

public interface DataSource<T> {
    T managerReadData();
    T staffReadData();
}
