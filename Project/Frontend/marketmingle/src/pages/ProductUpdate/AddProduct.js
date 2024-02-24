import React, { useEffect, useState } from 'react'
import { IoAddOutline } from "react-icons/io5";
import { addProduct } from '../../Services/ProductRegister';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';



const AddProduct = () => {
    const navigate = useNavigate();
    const [productName, setProductName] = useState("");
    const [brand, setBrand] = useState("");
    const [url, setUrl] = useState("");
    const [price, setPrice] = useState("");
    const [quantity, setQuantity] = useState("");
    const [description, setDescription] = useState("");

    const handleProductName = (e) => {
        setProductName(e.target.value);
    };
    const handleBrand = (e) => {
        setBrand(e.target.value);
    };
    const handlePrice = (e) => {
        setPrice(e.target.value);
    };
    const handleQuantity = (e) => {
        setQuantity(e.target.value);
    };
    const handleDescription = (e) => {
        setDescription(e.target.value);
    };
    const handleUrl = (e) => {
        setUrl(e.target.value);
    };


    // useEffect(() => {
    //     handleSubmit();
    // }, []);
    const handleSubmit = async (event) => {
        event.preventDefault();

        const requestData = {
            name: productName,
            description: description,
            price: price,
            quantity: quantity,
            brand: brand,
            imageUrl:url
        };



        axios.post("http://localhost:8080/admin/create",requestData).then((response) => {
            console.log("Data from API:", response.data);
            // setProducts(response.data);
            navigate("/inventory")
        })

        
        //   navigate("/signup")
        
        // try {
        //   const response = await addProduct(requestData);
        //   console.log(requestData);
        //   if (response.data === 1) {
        //     alert("Product Registration Successful");
        //     navigate("/inventory");
        //   } else if (response.data === 0) {
        //     alert("Something went wrong");
        //     navigate("/addProduct");
        //   }
        //   navigate("/addProduct")
        // } catch (error) {
        //   console.log(error);
        // }
    };

    return (
        <div>
            <section className="bg-white">
                <div className="max-w-2xl px-4 py-8 mx-auto lg:py-16">
                    <h2 className="mb-4 text-xl font-bold text-gray-900">Add product</h2>
                    <form action="#">
                        <div className="grid gap-4 mb-4 sm:grid-cols-2 sm:gap-6 sm:mb-5">
                            <div className="sm:col-span-2">
                                <label htmlFor="name" className="block mb-2 text-sm font-medium text-gray-900">Product Name</label>
                                <input type="text" name="name" value={productName} onChange={handleProductName} id="name" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="Type product name" required />
                            </div>
                            <div className="sm:col-span-2">
                                <label htmlFor="name" className="block mb-2 text-sm font-medium text-gray-900">Product Image URL</label>
                                <input type="text" name="name" value={url} onChange={handleUrl} id="name" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="Type product name" required />
                            </div>
                            <div className="w-full">
                                <label htmlFor="brand" className="block mb-2 text-sm font-medium text-gray-900">Brand</label>
                                <input type="text" name="brand" value={brand} onChange={handleBrand} id="brand" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="Product brand" required />
                            </div>
                            <div className="w-full">
                                <label htmlFor="price" className="block mb-2 text-sm font-medium text-gray-900">Price</label>
                                <input type="number" name="price" value={price} onChange={handlePrice} id="price" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="$299" required />
                            </div>
                            <div>
                                <label htmlFor="item-weight" className="block mb-2 text-sm font-medium text-gray-900">Quantity </label>
                                <input type="number" name="item-weight" value={quantity} onChange={handleQuantity} id="quantity" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 " placeholder="Ex. 12" required />
                            </div>
                            <div className="sm:col-span-2">
                                <label htmlFor="description" className="block mb-2 text-sm font-medium text-gray-900">Description</label>
                                <textarea id="description" rows={8} value={description} onChange={handleDescription} className="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-primary-500 focus:border-primary-500 " placeholder="Write a product description here..." />
                            </div>
                        </div>
                        <div className="flex items-center space-x-4">
                            <button type="button" onClick={handleSubmit} className="text-white flex items-center border border-gray-600 bg-green-600 hover:bg-primary-800 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center" >
                                <IoAddOutline />
                                Add
                            </button>
                        </div>
                    </form>
                </div>
            </section>

        </div>
    )
}

export default AddProduct
