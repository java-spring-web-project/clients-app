package pl.java.spring.web.project.clientappproject.database.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import pl.java.spring.web.project.clientappproject.database.model.Task;

import java.util.List;

public class TaskDAOImpl implements TaskDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Task> getTasks() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from TASK");
        List<Task> tasks = query.list();

        return tasks;
    }

    @Override
    public void addTask(Task newTask) {
        Session session = sessionFactory.getCurrentSession();
        session.save(newTask);
    }

    @Override
    public Task getTask(int taskId) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Task.class, taskId);
    }

    @Override
    public void deleteTask(int taskId) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("delete from TASK where task_id=:Id");
        query.setParameter("Id", taskId);
        query.executeUpdate();
    }
}
