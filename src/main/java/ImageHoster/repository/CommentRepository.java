package ImageHoster.repository;


import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class CommentRepository {


    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;



    public Comment createComment(Comment comment)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction entityTransaction=em.getTransaction();

        try {

            entityTransaction.begin();
            em.persist(comment);
            entityTransaction.commit();
        }catch (Exception e)
        {
            entityTransaction.rollback();
        }

        return comment;
    }

    public List<Comment> getAllcomments(Integer id) {

        EntityManager em = emf.createEntityManager();
        Image image=new Image();
        image.setId(id);
        TypedQuery<Comment> query = em.createQuery("SELECT i from Comment i where i.image=:imageid", Comment.class)
                .setParameter("imageid",image);
        List<Comment> resultList = query.getResultList();

        return resultList;


    }
}
