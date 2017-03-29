package filmLibrary.database;

import filmLibrary.model.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatamapperComments extends Datamapper<Comment>{

    public List<Comment> getComments(String filmID) {
        return multipleLoad(createLoadComments(filmID));
    }

    public void createComment(Comment comment) {
        update(createCommentQuery(comment.getIdFilm(), comment.getNickname(), comment.getComment()));
    }

    public void deleteComment(int id) {
        update(deleteCommentQuery(id));
    }

    private String deleteCommentQuery(int id) {
        return "DELETE FROM commentsfilm WHERE id = '"+id+"'";
    }

    private String createCommentQuery(int idFilm, String nickname, String comment) {
        return "INSERT INTO commentsfilm (idFilm, nickname, comment) VALUES ('"+idFilm+"', '"+nickname+"', '"+comment+"')";
    }

    private String createLoadComments(String idFilm) {
        return "SELECT * FROM commentsfilm WHERE idFilm = '"+idFilm+"'";
    }




    @Override
    protected Comment mapElementSimple(ResultSet rs) {
        try {
            while(rs.next()) {
                return new Comment(rs.getInt("id"),rs.getInt("idFilm"), rs.getString("nickname"), rs.getString("comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Comment mapElementExtraLoop(ResultSet rs) {
        try {
            return new Comment(rs.getInt("id"),rs.getInt("idFilm"),rs.getString("nickname"), rs.getString("comment"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
