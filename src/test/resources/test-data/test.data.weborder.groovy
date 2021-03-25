import com.neps.groovydatamapper.model.Carrier
import com.neps.groovydatamapper.model.DeliveryMethod
import com.neps.groovydatamapper.model.StoreFrontAlias
import com.neps.groovydatamapper.model.WebOrder

output = load('contact': 'test.data.contact.groovy', 'part': 'test.data.partnumber.groovy', 'payment': 'test.data.payment.groovy','product': 'test.data.product.groovy','storeFront':'test.data.storefrontalias.groovy')

assert output.contact.size() == 2
assert output.contact.contact2.appleId == 'ipatel@apple.com'

def defaultContact = output.contact.contact1

assert output.part.size() == 3
assert output.part.part1.type == 'default'

def defaultPart = output.part.part1

assert output.payment.size() == 1
assert output.payment.payment1.type == 'VISA'

def defaultPayment = output.payment.payment1
def paymentType = defaultPayment.type + ":" + defaultPayment.card

assert output.product.size() == 1
assert output.product.product1.name == 'iPhone 7 Plus'

def defaultProduct = output.product.product1

assert output.storeFront.size() == 2
assert output.storeFront.storefrontalias1.storeFrontId == 'us'

def defaultStoreFront = findBy(output.storeFront, StoreFrontAlias, 'storeFrontId', 'us')
assert defaultStoreFront != null
assert defaultStoreFront.storeFrontId == 'us'

table(WebOrder, ['webOrderNumber','storeFrontAlias','partNumber','contact','product','payment','deliveryMethod','carrier','retailStoreId','tdwctn','tdwzip','lastFourSSN','fullSSN','recycle','keywords']){
	weborder1 = row 'W9220093940', defaultStoreFront, defaultPart, defaultContact, defaultProduct, defaultPayment, DeliveryMethod.STH, Carrier.ICARUS, '3SKL', '232352', '3523','2323','2352323532','TEST','WEBORDER1'
}
