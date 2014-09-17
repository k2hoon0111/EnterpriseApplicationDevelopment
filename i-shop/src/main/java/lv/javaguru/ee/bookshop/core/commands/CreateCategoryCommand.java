package lv.javaguru.ee.bookshop.core.commands;

/**
 * Created by MumboJumbo on 17/09/14.
 */
public class CreateCategoryCommand implements DomainCommand<CreateCategoryCommandResult> {

    private String name;

    public CreateCategoryCommand(
            String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
