package net.unibave.folhapagamento.base.jaxrs;

public final class Pageable {

    private String where;

    private Integer firstResult;

    private Integer maxResults;

    private Pageable() {
    }

    private Pageable(String where, Integer firstResult, Integer maxResults) {
        this.where = where;
        this.firstResult = firstResult;
        this.maxResults = maxResults;
    }

    public static Pageable of(String where, Integer firstResult, Integer maxResults) {
        return new Pageable(where, firstResult, maxResults);
    }

    public String getWhere() {
        return where;
    }

    void setWhere(String where) {
        this.where = where;
    }

    public Integer getFirstResult() {
        return firstResult;
    }

    void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Pageable pageable = new Pageable();

        public Builder where(String where) {
            this.pageable.setWhere(where);
            return this;
        }

        public Builder firstResult(Integer firstResult) {
            this.pageable.setFirstResult(firstResult);
            return this;
        }

        public Builder maxResults(Integer maxResults) {
            this.pageable.setMaxResults(maxResults);
            return this;
        }

        public Pageable build() {
            return pageable;
        }

    }

}
