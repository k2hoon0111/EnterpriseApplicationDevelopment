//package lv.javaguru.ee.bookshop.core.camel;
//
//import org.apache.camel.Exchange;
//import org.apache.camel.spring.SpringRouteBuilder;
//
///**
// * Created by MumboJumbo on 12/10/14.
// */
//public class EtlRoutes extends SpringRouteBuilder {
//    public void configure() throws Exception {
//
//        from("file:src/data?noop=true")
//                .convertBodyTo(CategoryDTO.class)
//                .to("jpa:org.apache.camel.example.etl.CustomerEntity");
//
//        // the following will dump the database to files
//        from("jpa:org.apache.camel.example.etl.CustomerEntity?consumeDelete=false&delay=3000&consumeLockEntity=false")
//                .setHeader(Exchange.FILE_NAME, el("${in.body.userName}.xml"))
//                .to("file:target/customers");
//    }
//}