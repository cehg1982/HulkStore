/* ---------------------------------------------------- */
/*  QVision											*/
/*  Base de datos HulkStore	- Ing Cristhian Hernandez	*/
/*  Created On : 03-02.-2020							*/
/*  DBMS       : SQL Server 2012 						*/
/* ---------------------------------------------------- */

CREATE DATABASE [bdHulkStore]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'bdHulkStore', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\bdHulkStore.mdf' , SIZE = 8192KB , FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'bdHulkStore_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\bdHulkStore_log.ldf' , SIZE = 8192KB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [bdHulkStore] SET COMPATIBILITY_LEVEL = 140
GO
ALTER DATABASE [bdHulkStore] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [bdHulkStore] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [bdHulkStore] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [bdHulkStore] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [bdHulkStore] SET ARITHABORT OFF 
GO
ALTER DATABASE [bdHulkStore] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [bdHulkStore] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [bdHulkStore] SET AUTO_CREATE_STATISTICS ON(INCREMENTAL = OFF)
GO
ALTER DATABASE [bdHulkStore] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [bdHulkStore] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [bdHulkStore] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [bdHulkStore] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [bdHulkStore] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [bdHulkStore] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [bdHulkStore] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [bdHulkStore] SET  DISABLE_BROKER 
GO
ALTER DATABASE [bdHulkStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [bdHulkStore] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [bdHulkStore] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [bdHulkStore] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [bdHulkStore] SET  READ_WRITE 
GO
ALTER DATABASE [bdHulkStore] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [bdHulkStore] SET  MULTI_USER 
GO
ALTER DATABASE [bdHulkStore] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [bdHulkStore] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [bdHulkStore] SET DELAYED_DURABILITY = DISABLED 
GO
USE [bdHulkStore]
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = Off;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = Primary;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = On;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = Primary;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = Off;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = Primary;
GO
USE [bdHulkStore]
GO
IF NOT EXISTS (SELECT name FROM sys.filegroups WHERE is_default=1 AND name = N'PRIMARY') ALTER DATABASE [bdHulkStore] MODIFY FILEGROUP [PRIMARY] DEFAULT
GO

/* Drop Foreign Key Constraints */
USE [bdHulkStore]
GO
CREATE ROLE [user_rol] AUTHORIZATION [dbo]
GO
USE [bdHulkStore]
GO
CREATE LOGIN [user_HulkStore] WITH PASSWORD=N'user_HulkStore', DEFAULT_DATABASE=[bdHulkStore], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF
GO
USE [bdHulkStore]
GO
CREATE USER [user_HulkStore] FOR LOGIN [user_HulkStore] WITH DEFAULT_SCHEMA=[dbo]
GO
USE [bdHulkStore]
GO
ALTER ROLE [user_rol] ADD MEMBER [user_HulkStore]
GO

USE [bdHulkStore]
GO

/* Create Tables */

CREATE TABLE [dbo].[tbProductos](
	[cnsctvo_prdcto] [int] IDENTITY(1,1) NOT NULL,
	[nmbre_prdcto] [varchar](50) NOT NULL,
	[dscrpcon_prdcto] [varchar](500) NULL,
	[cntdd_stck] [int]  NULL,
	[stado] [varchar](1) NULL,
	[fcha_crcn] [datetime] NULL,
	[usro_crcn] [varchar](20) NOT NULL,
	[fcha_ultma_mdfccn] [datetime] NULL,
	[usro_ultma_mdfccn] [varchar](20) NULL,
 CONSTRAINT [PK_tbProductos] PRIMARY KEY CLUSTERED 
(
	[cnsctvo_prdcto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[tbProductos] ADD  DEFAULT ('A') FOR [stado]
GO

ALTER TABLE [dbo].[tbProductos] ADD  DEFAULT (getdate()) FOR [fcha_crcn]
GO

use [bdHulkStore]
GO
GRANT ALTER ON [dbo].[tbProductos]  TO [user_HulkStore]
GO
use [bdHulkStore]
GO
GRANT CONTROL ON [dbo].[tbProductos] TO [user_HulkStore]
GO
use [bdHulkStore]
GO
GRANT DELETE ON [dbo].[tbProductos] TO [user_HulkStore]
GO
use [bdHulkStore]
GO
GRANT INSERT ON [dbo].[tbProductos] TO [user_HulkStore]
GO
use [bdHulkStore]
GO
GRANT SELECT ON [dbo].[tbProductos] TO [user_HulkStore]
GO
use [bdHulkStore]
GO
GRANT UPDATE ON [dbo].[tbProductos] TO [user_HulkStore]
GO

CREATE TABLE [dbo].[tbDetalleCompraProducto](
	[cnsctvo_cdgo_cmpra_prdcto] [int] IDENTITY(1,1) NOT NULL,
	[cnsctvo_prdcto] [int] NOT NULL,
	[cntdd] [int] NOT NULL,
	[prcio_cmpra] [int] NOT NULL,
	[fcha_crcn] [datetime] NULL,
	[usro_crcn] [varchar](20) NOT NULL,
 CONSTRAINT [PK_tbDetalleCompraProducto] PRIMARY KEY CLUSTERED 
(
	[cnsctvo_cdgo_cmpra_prdcto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[tbDetalleCompraProducto] ADD  DEFAULT (getdate()) FOR [fcha_crcn]
GO

ALTER TABLE [dbo].[tbDetalleCompraProducto]  WITH CHECK ADD  CONSTRAINT [FK_tbDetalleCompraProducto_tbProductos] FOREIGN KEY([cnsctvo_prdcto])
REFERENCES [dbo].[tbProductos] ([cnsctvo_prdcto])
GO

ALTER TABLE [dbo].[tbDetalleCompraProducto] CHECK CONSTRAINT [FK_tbDetalleCompraProducto_tbProductos]
GO

use [bdHulkStore]
GO
GRANT ALTER ON [dbo].[tbDetalleCompraProducto]  TO [user_HulkStore]
GO
use [bdHulkStore]
GO
GRANT CONTROL ON [dbo].[tbDetalleCompraProducto] TO [user_HulkStore]
GO
use [bdHulkStore]
GO
GRANT DELETE ON [dbo].[tbDetalleCompraProducto] TO [user_HulkStore]
GO
use [bdHulkStore]
GO
GRANT INSERT ON [dbo].[tbDetalleCompraProducto] TO [user_HulkStore]
GO
use [bdHulkStore]
GO
GRANT SELECT ON [dbo].[tbDetalleCompraProducto] TO [user_HulkStore]
GO
use [bdHulkStore]
GO
GRANT UPDATE ON [dbo].[tbDetalleCompraProducto] TO [user_HulkStore]
GO

CREATE TABLE [dbo].[tbDetalleVentaProducto](
	[cnsctvo_cdgo_vnta_prdcto] [int] IDENTITY(1,1) NOT NULL,
	[cnsctvo_prdcto] [int] NOT NULL,
	[cntdd] [int] NOT NULL,
	[prcio_untro] [int] NOT NULL,
	[fcha_crcn] [datetime] NULL,
	[usro_crcn] [varchar](20) NOT NULL,
 CONSTRAINT [PK_tbDetalleVentaProducto] PRIMARY KEY CLUSTERED 
(
	[cnsctvo_cdgo_vnta_prdcto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[tbDetalleVentaProducto] ADD  DEFAULT (getdate()) FOR [fcha_crcn]
GO

ALTER TABLE [dbo].[tbDetalleVentaProducto]  WITH CHECK ADD  CONSTRAINT [FK_tbDetalleVentaProducto_tbProductos] FOREIGN KEY([cnsctvo_prdcto])
REFERENCES [dbo].[tbProductos] ([cnsctvo_prdcto])
GO

ALTER TABLE [dbo].[tbDetalleVentaProducto] CHECK CONSTRAINT [FK_tbDetalleVentaProducto_tbProductos]
GO

use [bdHulkStore]
GO
GRANT ALTER ON [dbo].[tbDetalleVentaProducto]  TO [user_HulkStore]
GO
use [bdHulkStore]
GO
GRANT CONTROL ON [dbo].[tbDetalleVentaProducto] TO [user_HulkStore]
GO
use [bdHulkStore]
GO
GRANT DELETE ON [dbo].[tbDetalleVentaProducto] TO [user_HulkStore]2
GO
use [bdHulkStore]
GO
GRANT INSERT ON [dbo].[tbDetalleVentaProducto] TO [user_HulkStore]
GO
use [bdHulkStore]
GO
GRANT SELECT ON [dbo].[tbDetalleVentaProducto] TO [user_HulkStore]
GO
use [bdHulkStore]
GO
GRANT UPDATE ON [dbo].[tbDetalleVentaProducto] TO [user_HulkStore]
GO