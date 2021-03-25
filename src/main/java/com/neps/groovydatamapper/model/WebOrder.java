package com.neps.groovydatamapper.model;

import java.io.Serializable;

import com.neps.groovydatamapper.utils.AlternativeColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WebOrder implements Serializable {
	
	@AlternativeColumn(name = "MyCustomWebOrderNumber")
	private String webOrderNumber;
	
	private StoreFrontAlias storeFrontAlias;
	private Contact contact;
	private Payment payment;
	private DeliveryMethod deliveryMethod;
	private Carrier carrier;
	private Product product;
	private PartNumber partNumber;
	
	private String tdwctn;
	private String tdwzip;
	private String lastFourSSN;
	private String fullSSN;
	private String recycle;
	private String keywords;
	private String lockedPhone;
	private String checkedInductionStatus;
	private String retailStoreId;
	private String tradeInApu;
	
}
