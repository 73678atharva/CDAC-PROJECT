import React from 'react'
import Product from '../../components/home/Products/Product';

const Items=({ products })=> {
    return (
      <>
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
      </>
    );
  }

export default Items
