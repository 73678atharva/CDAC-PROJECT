import React, { useEffect, useState } from "react";
import Breadcrumbs from "../../components/pageProps/Breadcrumbs";
import Pagination from "../../components/pageProps/shopPage/Pagination";
import ProductBanner from "../../components/pageProps/shopPage/ProductBanner";
import ShopSideNav from "../../components/pageProps/shopPage/ShopSideNav";
import axios from "axios";
import Items from "./Items";

const Shop = () => {
  // const [itemsPerPage, setItemsPerPage] = useState(12);
  // const itemsPerPageFromBanner = (itemsPerPage) => {
  //   setItemsPerPage(itemsPerPage);
  // };

  const [products, setProducts] = useState([]);

  useEffect(() => {
    getAllProducts();
  }, []);

  const getAllProducts = async() => {

    let route = 'http://localhost:8080/admin/all';

    try{
      const response = await axios.get(route);
        if(response.status == 200){
        console.log("Data from API:", response.data);
        console.log(response.data.products)
        setProducts(response.data);
        console.log(products);
        }
      
    }catch(error) {
        console.error("Error fetching products:", error);
      }
  };

  return (
    <div className="max-w-container mx-auto px-4">
      <Breadcrumbs title="Products" />
      {/* ================= Products Start here =================== */}
      <div className="w-full h-full flex pb-20 gap-10">
        {/* <div className="w-[20%] lgl:w-[25%] hidden mdl:inline-flex h-full">
          <ShopSideNav />
        </div> */}
        <div className="w-full h-full flex flex-col gap-10">
          {/* <ProductBanner itemsPerPageFromBanner={itemsPerPageFromBanner} /> */}
          {/* <Pagination products={products} /> */}
          <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-10 mdl:gap-4 lg:gap-10">
          <Items products={products}/>
          </div>
        </div>
      </div>
      {/* ================= Products End here ===================== */}
    </div>
  );
};

export default Shop;
