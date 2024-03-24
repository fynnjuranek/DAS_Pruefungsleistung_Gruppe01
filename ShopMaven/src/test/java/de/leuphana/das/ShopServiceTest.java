package de.leuphana.das;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.BookCategory;
import de.leuphana.shop.structure.article.CD;
import de.leuphana.shop.structure.billing.Invoice;
import de.leuphana.shop.structure.billing.InvoicePosition;
import de.leuphana.shop.structure.sales.CartItem;
import de.leuphana.shop.structure.sales.Customer;
import de.leuphana.shop.structure.sales.OrderPosition;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShopServiceTest {

    @Autowired
    ShopService shopService;

    static Article addedBook;
    static Article addedCD;

    static Integer customerId;

    static Integer bookQuantity;

    @Test
    @Order(1)
    void canCustomerBeCreated() {
        String customerName = "Max Mustermann";
        String customerAddress = "Musterstraße 32, 21335 Lüneburg";
        customerId = shopService.createCustomer(customerName, customerAddress);
        System.out.println("Customer with id: " + customerId + " has been created.");
        Assertions.assertNotNull(customerId);
    }

    @Test
    @Order(2)
    void canArticlesBeAddedToDatabase() {
        Book book = new Book();
        book.setName("THINKING, FAST AND SLOW");
        book.setManufacturer("Penguin Verlag");
        book.setAuthor("Steven Pinker");
        book.setPrice(16.0f);
        book.setBookCategory(BookCategory.POPULAR_SCIENCE);
        CD cd = new CD();
        cd.setName("GO:OD AM");
        cd.setArtist("Mac Miller");
        cd.setPrice(16.99f);
        cd.setManufacturer("Warner Records Inc.");
        addedBook = shopService.addNewArticleToCatalog(book);
        addedCD = shopService.addNewArticleToCatalog(cd);
        System.out.println("Added articles to articleDatabase: ");
        System.out.println("Article id: " + addedBook.getArticleId() + ", name: " + book.getName());
        System.out.println("Article id: " + addedCD.getArticleId() + ", name: " + cd.getName());
        Assertions.assertEquals(book.getName(), addedBook.getName());
    }

    @Test
    @Order(3)
    void canArticlesBeAddedToCart() {
        bookQuantity = 4;
        Integer cdQuantity = 2;
        shopService.addArticleToCart(customerId, addedBook.getArticleId(), bookQuantity);
        Customer customer = shopService.addArticleToCart(customerId, addedCD.getArticleId(), cdQuantity);

        Integer numberOfArticlesInCart = bookQuantity + cdQuantity;
        System.out.println("Added articles with ids: " + addedBook.getArticleId() + ", " + addedCD.getArticleId() +
                " to cart of customer with id: " + customerId);
        Assertions.assertEquals(numberOfArticlesInCart, customer.getCart().getNumberOfArticles());
    }

    @Test
    @Order(4)
    void canArticleBeRemovedFromCart() {
        Customer customer = shopService.removeArticleFromCart(customerId, addedCD.getArticleId());
        boolean isRemoved = true;
        for (CartItem cartItem : customer.getCart().getCartItems()) {
            if (cartItem.getArticleId().equals(addedCD.getArticleId())) {
                isRemoved = false;
                break;
            }
        }
        System.out.println("Removed article id: " + addedCD.getArticleId() + " from cart of customer with id: " + customerId +
                " (variable = isRemoved = " + isRemoved + ")");
        Assertions.assertTrue(isRemoved);
    }

    @Test
    @Order(5)
    void canArticleQuantityBeDecremented() {
        Customer customer = shopService.decrementArticleQuantityInCart(customerId, addedBook.getArticleId());
        Integer newArticleQuantity = 0;
        for (CartItem cartItem : customer.getCart().getCartItems()) {
            if (cartItem.getArticleId().equals(addedBook.getArticleId())) {
                newArticleQuantity = cartItem.getQuantity();
            }
        }
        System.out.println("Decremented the bookQuantity by one unit from " + bookQuantity + " to " + newArticleQuantity);
        Assertions.assertEquals(bookQuantity - 1, newArticleQuantity);
    }

    @Test
    @Order(6)
    void canCheckOutCartBeCreated() {
        de.leuphana.shop.structure.sales.Order order = shopService.checkOutCart(customerId);
        // This is explicitly for the newest order we added to the customer.
        // Otherwise, we would get an error when running this test multiple times
        // because one customer can hold multiple orderIds
        List<String> orderIDsOfCustomer = shopService.getCustomer(customerId).getOrderIDs();
        String addedOrderIdOfCustomer = orderIDsOfCustomer.get(orderIDsOfCustomer.indexOf(order.getOrderId()));
        System.out.println("Order IDs: ");
        for (String orderID : orderIDsOfCustomer) {
            System.out.println("id: " + orderID);
            System.out.println("OrderPositions: ");
            for (OrderPosition orderPosition : order.getOrderPositions()) {
                System.out.println("Article id: " + orderPosition.getArticleId());
                System.out.println("Article quantity: " + orderPosition.getArticleQuantity());
            }
        }
        Assertions.assertEquals(order.getOrderId(), addedOrderIdOfCustomer);
    }

    @Test
    @Order(7)
    void canInvoiceBeCreated() {
        List<String> orderIDs = shopService.getCustomer(customerId).getOrderIDs();
        List<Invoice> invoices = new ArrayList<>();
        for (String orderID : orderIDs) {
            invoices.add(shopService.createInvoice(orderID));
        }
        System.out.println("Created Invoices with invoice-positions: ");
        for (Invoice invoice : invoices) {
            int i = 1;
            for (InvoicePosition invoicePosition : invoice.getAllInvoicePositions()) {
                System.out.println("Invoice position: " + i++);
                System.out.println("Article id: " + invoicePosition.getArticleId());
                System.out.println("Article quantity: " + invoicePosition.getArticleQuantity());
                System.out.println("Article price: " + invoicePosition.getArticlePrice());
                System.out.println("Total price: " + invoicePosition.getTotalPrice());
            }
        }
        Assertions.assertFalse(invoices.isEmpty());

    }

    // TODO: add Logging and Tracing!!!


}
