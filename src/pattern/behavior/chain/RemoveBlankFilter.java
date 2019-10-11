package pattern.behavior.chain;

public class RemoveBlankFilter extends Filter {
    @Override
    protected String doOperator(String content) {
        System.out.println("Before RemoveBlankFilter: " + content);
        content = content.replace(" ", "");
        System.out.println("After RemoveBlankFilter: " + content);
        return content;
    }
}
