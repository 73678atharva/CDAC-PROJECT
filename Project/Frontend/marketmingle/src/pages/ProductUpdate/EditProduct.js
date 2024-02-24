import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate, useParams } from 'react-router-dom';

const EditProduct = () => {
    const navigate = useNavigate();
    const [productName, setProductName] = useState("");
    const [brand, setBrand] = useState("");
    const [price, setPrice] = useState("");
    const [quantity, setQuantity] = useState("");
    const [description, setDescription] = useState("");
    const [url,setUrl]=useState();
    // const [data,setData]=useState({});    
    const { id } = useParams();

    // useEffect(() => {
    //     handleSubmit();
    // }, []);

    useEffect(() => {
        getProduct();
      }, []);

    const getProduct = () => {
        console.log(id);
       
        axios.get(`http://localhost:8080/api/product/${id}`).then((response) => {
        //   setData((response.data));
          let result = response.data;
          //console.log(result);  
         if (result.status === "success") {
        //    setData(result);

        //    set the details
        //    setMenu(result["data"]);
        //     prodId=result.id;
            setProductName(result.name);
            setBrand(result.brand);
            setPrice(result.price);
            setDescription(result.description);
            setQuantity(result.quantity);
            
          console.log(result["data"]);
         }
        // console.log(response.data);


        });
      
    }


    const handleSubmit = async (event) => {
        event.preventDefault();

        const requestData = {
            id:id,
            name: productName,
            description: description,
            price: price,
            quantity: quantity,
            brand: brand
        };
        console.log()
        axios.put(`http://localhost:8080/admin/update/${id}`, requestData).then((response) => {
            console.log("Data from API:", response.data);
            // setProducts(response.data);
            navigate("/inventory")
        })
    }
       
    
        return (
            
            <section className="bg-white">
                <div className="max-w-2xl px-4 py-8 mx-auto lg:py-16">
                    <h2 className="mb-4 text-xl font-bold text-gray-900">Edit product</h2>
                    <form action="#">
                        <div className="grid gap-4 mb-4 sm:grid-cols-2 sm:gap-6 sm:mb-5">
                            <div className="sm:col-span-2">
                                <label htmlFor="name" className="block mb-2 text-sm font-medium text-gray-900">Product Name</label>
                                <input type="text" name="name" value={productName} 
                                onChange={ (e) => {setProductName(e.target.value);}} 
                                id="name" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="Type product name" required />
                            </div>
                            <div className="sm:col-span-2">
                                <label htmlFor="name" className="block mb-2 text-sm font-medium text-gray-900">Product Image URL</label>
                                <input type="text" name="name" value={url} 
                                onChange={(e) => {setUrl(e.target.value);}}
                                id="name" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="Type product name" required />
                            </div>
                            <div className="w-full">
                                <label htmlFor="brand" className="block mb-2 text-sm font-medium text-gray-900">Brand</label>
                                <input type="text" name="brand" value={brand} 
                                onChange={(e) => {setBrand(e.target.value);}} 
                                id="brand" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="Product brand" required />
                            </div>
                            <div className="w-full">
                                <label htmlFor="price" className="block mb-2 text-sm font-medium text-gray-900">Price</label>
                                <input type="number" name="price" value={price} 
                                onChange={(e) => {setPrice(e.target.value);}} 
                                id="price" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="$299" required />
                            </div>
                            <div>
                                <label htmlFor="item-weight" className="block mb-2 text-sm font-medium text-gray-900">Quantity </label>
                                <input type="number" name="item-weight" value={quantity} 
                                onChange={ (e) => {setQuantity(e.target.value);}} 
                                id="quantity" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 " placeholder="Ex. 12" required />
                            </div>
                            <div className="sm:col-span-2">
                                <label htmlFor="description" className="block mb-2 text-sm font-medium text-gray-900">Description</label>
                                <textarea id="description" rows={8} value={description} 
                                onChange={(e) => {setDescription(e.target.value);}}
                                 className="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-primary-500 focus:border-primary-500 " placeholder="Write a product description here..." />
                            </div>
                        </div>
                        <div className="flex items-center space-x-4">
                            <button type="button" onClick={handleSubmit} className="text-white flex items-center border border-gray-600 bg-green-600 hover:bg-primary-800 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center" >
                                Edit
                            </button>
                        </div>
                    </form>
                </div>
            </section>
        )
    }


export default EditProduct;
