CREATE DATABASE CoffeeShop
GO
USE CoffeeShop
GO
/****** Object:  Table    DetailReceipt     Script Date: 28/05/2016 21:58:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE DetailReceipt(
    ReceiptId int NOT NULL,
	ProductId int NOT NULL,
	 Count   int  NOT NULL,
 CONSTRAINT  PK_DetailReceipt  PRIMARY KEY CLUSTERED 
(
	 ReceiptId  ASC,
	 ProductId  ASC
)
)

GO
/****** Object:  Table    DetailReceiptNote     Script Date: 28/05/2016 21:58:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE  DetailReceiptNote 
(
	 ReceiptNoteId   int  NOT NULL,
	 ProductId   int  NOT NULL,
	 Price decimal  NOT NULL,
	 Count   int  NOT NULL,
	 CONSTRAINT  PK_DetailReceiptNote  PRIMARY KEY CLUSTERED 
	(
		 ReceiptNoteId  ASC,
		 ProductId  ASC
	)
)

GO
/****** Object:  Table    Employee     Script Date: 28/05/2016 21:58:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE  Employee (
	 EmployeeId   int  AUTO_INCREMENT NOT NULL,
	 EmployeeName  nvarchar (100) NOT NULL ,
	 Address   nvarchar (200) NOT NULL,
	 Phone   varchar (15) NOT NULL,
	 Role   nvarchar (50) NULL,
 CONSTRAINT  PK_Employee  PRIMARY KEY CLUSTERED 
(
	 EmployeeId  ASC
)
) 

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table    Group     Script Date: 28/05/2016 21:58:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE GroupProduct (
	 GroupId int(11) NOT NULL AUTO_INCREMENT,
	 GroupName nvarchar (100) NOT NULL,
 CONSTRAINT  PK_Group  PRIMARY KEY CLUSTERED 
(
	 GroupId  ASC
)
) 

GO
/****** Object:  Table    Product     Script Date: 28/05/2016 21:58:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE Product (
	 ProductId   int  AUTO_INCREMENT NOT NULL,
	 GroupId   int  NOT NULL,
	 UnitId   int  NOT NULL,
	 ProductName   nvarchar (100) NOT NULL,
	 Price   decimal NOT NULL,
	 Count   int  NOT NULL,
	 Status   bit  NOT NULL,
 CONSTRAINT  PK_Product  PRIMARY KEY CLUSTERED 
(
	 ProductId  ASC
)
) 

GO
/****** Object:  Table    Provider     Script Date: 28/05/2016 21:58:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE Provider (
	 ProviderId   int  AUTO_INCREMENT NOT NULL,
	 ProviderName   nvarchar (100) NOT NULL,
	 Address   nvarchar (200) NOT NULL,
 CONSTRAINT  PK_Provider  PRIMARY KEY CLUSTERED 
(
	 ProviderId  ASC
)
) 

GO
/****** Object:  Table    Receipt     Script Date: 28/05/2016 21:58:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE Receipt (
	 ReceiptId   int  AUTO_INCREMENT NOT NULL,
	 EmployeeId   int  NOT NULL,
	 Date   date  NOT NULL,
	 CustomerName   nvarchar (100) NOT NULL,
	 TotalPrice   decimal  NOT NULL,
	 Status   bit  NOT NULL,
	 TableId   int  NULL,
 CONSTRAINT  PK_Receipt  PRIMARY KEY CLUSTERED 
(
	 ReceiptId  ASC
)
) 

GO
/****** Object:  Table    ReceiptNote     Script Date: 28/05/2016 21:58:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE ReceiptNote (
	 ReceiptNoteId   int  AUTO_INCREMENT NOT NULL,
	 ProviderId   int  NOT NULL,
	 Date   date  NOT NULL,
	 TotalCount   int  NOT NULL,
 CONSTRAINT  PK_ReceiptNote  PRIMARY KEY CLUSTERED 
(
	 ReceiptNoteId  ASC
)
) 

GO
/****** Object:  Table    Table     Script Date: 28/05/2016 21:58:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE TableCoffee (
	 TableId   int  AUTO_INCREMENT NOT NULL,
	 TableName   varchar (100) NOT NULL,
	 Status   bit  NOT NULL,
	 ProductIds   varchar (20) NULL,
 CONSTRAINT  PK_Table  PRIMARY KEY CLUSTERED 
(
	 TableId  ASC
)
)

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table    Unit     Script Date: 28/05/2016 21:58:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE  Unit (
	 UnitId   int  AUTO_INCREMENT NOT NULL,
	 UnitName   nvarchar (50) NOT NULL,
 CONSTRAINT  PK_Unit  PRIMARY KEY CLUSTERED 
(
	 UnitId  ASC
)
) 

GO
ALTER TABLE    DetailReceipt ADD  CONSTRAINT  FK_DetailReceipt_Product  FOREIGN KEY( ProductId )
REFERENCES    Product  ( ProductId )
GO
ALTER TABLE    DetailReceipt  CHECK CONSTRAINT  FK_DetailReceipt_Product 
GO
ALTER TABLE    DetailReceipt    ADD  CONSTRAINT  FK_DetailReceipt_Receipt  FOREIGN KEY( ReceiptId )
REFERENCES    Receipt  ( ReceiptId )
GO
ALTER TABLE    DetailReceipt  CHECK CONSTRAINT  FK_DetailReceipt_Receipt 
GO
ALTER TABLE    DetailReceiptNote  ADD  CONSTRAINT  FK_DetailReceiptNote_Product  FOREIGN KEY( ProductId )
REFERENCES    Product  ( ProductId )
GO
ALTER TABLE    DetailReceiptNote  CHECK CONSTRAINT  FK_DetailReceiptNote_Product 
GO
ALTER TABLE    DetailReceiptNote  ADD  CONSTRAINT  FK_DetailReceiptNote_ReceiptNote  FOREIGN KEY( ReceiptNoteId )
REFERENCES    ReceiptNote  ( ReceiptNoteId )
GO
ALTER TABLE    DetailReceiptNote  CHECK CONSTRAINT  FK_DetailReceiptNote_ReceiptNote 
GO
ALTER TABLE    Product  ADD  CONSTRAINT  FK_Product_Group  FOREIGN KEY( GroupId )
REFERENCES    GroupProduct  ( GroupId )
GO
ALTER TABLE    Product  CHECK CONSTRAINT  FK_Product_Group 
GO
ALTER TABLE    Product  ADD  CONSTRAINT  FK_Product_Unit  FOREIGN KEY( UnitId )
REFERENCES    Unit  ( UnitId )
GO
ALTER TABLE    Product  CHECK CONSTRAINT  FK_Product_Unit 
GO
ALTER TABLE    Receipt ADD  CONSTRAINT  FK_Receipt_Employee  FOREIGN KEY( EmployeeId )
REFERENCES    Employee  ( EmployeeId )
GO
ALTER TABLE    Receipt  CHECK CONSTRAINT  FK_Receipt_Employee 
GO
ALTER TABLE    Receipt  ADD  CONSTRAINT  FK_Receipt_Table  FOREIGN KEY( TableId )
REFERENCES    TableCoffee  ( TableId )
GO
ALTER TABLE    Receipt  CHECK CONSTRAINT  FK_Receipt_Table 
GO
ALTER TABLE    ReceiptNote   ADD  CONSTRAINT  FK_ReceiptNote_Provider  FOREIGN KEY( ProviderId )
REFERENCES    Provider  ( ProviderId )
GO
ALTER TABLE    ReceiptNote  CHECK CONSTRAINT  FK_ReceiptNote_Provider 
GO

SET CHARSET 'utf8';