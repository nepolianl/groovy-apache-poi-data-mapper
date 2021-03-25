import com.neps.groovydatamapper.model.Product;

table(Product, ['name', 'description', 'isAppleDevice', 'category', 'capacity']) {
	product1 = row 'iPhone 7 Plus', 'iPhone 7 Plus', 'TRUE', 'smartphone', '128'
}