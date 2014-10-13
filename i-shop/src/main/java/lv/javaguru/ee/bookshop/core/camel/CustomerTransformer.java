//package lv.javaguru.ee.bookshop.core.camel;
//
//import lv.javaguru.ee.bookshop.core.database.jpa.JPACRUDOperationDAO;
//import lv.javaguru.ee.bookshop.core.domain.Category;
//import org.apache.camel.Converter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * Created by MumboJumbo on 12/10/14.
// */
//@Converter
//public final class CustomerTransformer {
//
//    @Autowired
//    public JPACRUDOperationDAO jpacrudOperationDAO;
//
//    private static final Logger LOG = LoggerFactory.getLogger(CustomerTransformer.class);
//
//    private CustomerTransformer() {
//    }
//
//    /**
//     * A transformation method to convert a person categoryDTOument into a customer
//     * entity
//     */
//    @Converter
//    public static Category toCategory(CategoryDTO categoryDTO) throws Exception {
//////        EntityManager entityManager = exchange.getIn().getHeader(JpaConstants.ENTITYMANAGER, EntityManager.class);
//////        TransactionTemplate transactionTemplate = exchange.getContext().getRegistry().lookupByNameAndType("transactionTemplate", TransactionTemplate.class);
//////
//////        String user = categoryDTO.getUser();
////        Category category = crudOperationDAO.getById(categoryDTO.getCategoryId());
////
////        // let's convert information from the categoryDTO into the entity bean
////        category.setUserName(user);
////        category.setFirstName(categoryDTO.getFirstName());
////
////
////
////        LOG.info("Created object customer: {}", category);
////        return category;
//        return null;
//    }
//
//}