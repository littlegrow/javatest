package pattern.behavior.chain;

public abstract class Filter {
    private Filter nextFilter;

    public Filter setNextFilter(Filter nextFilter) {
        this.nextFilter = nextFilter;
        return nextFilter;
    }

    public String filter(String content) {
        content = doOperator(content);
        if (nextFilter != null) {
            return nextFilter.filter(content);
        }
        return content;
    }

    protected abstract String doOperator(String content);
}
