package filmLibrary.model;

import java.util.Arrays;
import java.util.stream.Stream;

public class FilmSearch implements Search{
    private String order;
    private String genre;
    private String title;

    @Override
    public String getDataBaseExpression() {
        return new DataBaseExpression()
                .addEquals(genre)
                .addIndications(title)
                .addOrder(order)
                .build();
    }

    class DataBaseExpression{
        private String expression;

        public DataBaseExpression() {
            this.expression = "";
        }

        public DataBaseExpression addIndications(String... indications) {
            filterVoids(indications)
                    .forEach((element)->{
                        addExpression("title LIKE '%" + element + "%'");
                    });
            return this;
        }

        public DataBaseExpression addEquals(String... equalsTo) {
            filterVoids(equalsTo)
                    .forEach((element)->{
                        addExpression("genre='" + element + "'");
                    });
            return this;
        }

        public DataBaseExpression addOrder(String... orderBys) {
            if(filterVoids(orderBys).toArray().length != 0){
                expression += " ORDER BY ";
                expression += filterVoids(orderBys).reduce("", (prev, current)-> current + ",");
                expression = expression.substring(0, expression.length()-1);
            }
            return this;
        }

        private Stream<String> filterVoids(String[] orderBys) {
            return Arrays.asList(orderBys).stream().filter(element -> element!=null);
        }

        private void addExpression(String addToExpression) {
            if(!this.expression.equals(""))
                this.expression += " AND " + addToExpression;
            else
                this.expression += addToExpression + " ";
        }

        public String build() {
            return expression;
        }
    }
}
