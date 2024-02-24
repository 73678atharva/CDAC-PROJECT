import React, { useEffect, useState } from "react";
import Heading from "../Products/Heading";
import Product from "../Products/Product";
import {
  spfOne,
  spfTwo,
  spfThree,
  spfFour,
} from "../../../assets/images/index";
import axios from "axios";
import { useNavigate } from "react-router-dom";


const SpecialOffers = () => {

  const navigate = useNavigate();
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
    <div className="w-full pb-20">
      <Heading heading="Special Offers" />
      <div className="w-full grid grid-cols-1 md:grid-cols-2 lgl:grid-cols-3 xl:grid-cols-4 gap-10">
      {products.map((value, index) => {
        return (
          <Product
            key={index} // Adding a unique key is necessary when rendering a list of components
            _id={value.id}
            img={value.imageUrl}
            productName={value.name}
            price={value.price}
            badge={true}
            des={value.description}
          />
        );
      })}
        {/* Static Special Offers */}
        {/* <Product
          _id="1101"
          img={spfOne}
          productName="Cap for Boys"
          price="35.00"
          color="Blank and White"
          badge={true}
          des="Lorem ipsum dolor sit amet consectetur adipisicing elit. Hic excepturi quibusdam odio deleniti reprehenderit facilis."
        />
        <Product
          _id="1102"
          img={spfTwo}
          productName="Tea Table"
          price="180.00"
          color="Gray"
          badge={true}
          des="Lorem ipsum dolor sit amet consectetur adipisicing elit. Hic excepturi quibusdam odio deleniti reprehenderit facilis."
        />
        <Product
          _id="1103"
          img={spfThree}
          productName="Headphones"
          price="25.00"
          color="Mixed"
          badge={true}
          des="Lorem ipsum dolor sit amet consectetur adipisicing elit. Hic excepturi quibusdam odio deleniti reprehenderit facilis."
        />
        <Product
          _id="1104"
          img={spfFour}
          productName="Sun glasses"
          price="220.00"
          color="Black"
          badge={true}
          des="Lorem ipsum dolor sit amet consectetur adipisicing elit. Hic excepturi quibusdam odio deleniti reprehenderit facilis."
        /> */}
      </div>
    </div>
  );
};

export default SpecialOffers;
