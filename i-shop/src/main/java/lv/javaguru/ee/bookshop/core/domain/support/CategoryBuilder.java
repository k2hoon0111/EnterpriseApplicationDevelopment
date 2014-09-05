package lv.javaguru.ee.bookshop.core.domain.support;

import org.springframework.stereotype.Component;

import lv.javaguru.ee.bookshop.core.domain.Category;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CategoryBuilder extends EntityBuilder<Category> {

	@Override
	void initProduct() {
	}

	@Override
	Category assembleProduct() {
		return this.product;
	}

	public CategoryBuilder name(String name) {
		this.product = new Category(name);
		return this;
	}

}
