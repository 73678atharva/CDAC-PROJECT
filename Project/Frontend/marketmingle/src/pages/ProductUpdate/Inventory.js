import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import { getProduct } from '../../Services/GetProduct';
import axios from 'axios';


const Inventory = () => {

    const navigate = useNavigate();
    const [products, setProducts] = useState([]);

    useEffect(() => {
        getAllProducts();
    }, []);

    const getAllProducts = () => {
        axios.get("http://localhost:8080/admin/all")
            .then((response) => {
                console.log("Data from API:", response.data);
                setProducts(response.data);
            })
         }

    console.log("Products:", products);

    const handleDelete = (id) => {
        console.log(id);
        axios.delete(`http://localhost:8080/admin/delete/${id}`)
            .then(() => {
                navigate(-1);
                getAllProducts();
            })
            .catch((error) => {
                console.error(error);
            });
    }



    return (
        <>
            <div className="relative overflow-x-auto shadow-md sm:rounded-lg m-12  ">
                <table className="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                    <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                        <tr>
                            <th scope="col" className="px-6 py-3">
                                Product name
                            </th>
                            <th scope="col" className="px-6 py-3">
                                Brand
                            </th>
                            <th scope="col" className="px-6 py-3">
                                quantity
                            </th>
                            <th scope="col" className="px-6 py-3">
                                Price
                            </th>
                            <th scope="col" className="px-6 py-3">
                                Action
                            </th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        {products.map((value, index) => {
                            return (
                                <tr key={index} className="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">

                                    <th scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                        {value.name}
                                    </th>
                                    <td className="px-6 py-4">
                                        {value.brand}
                                    </td>
                                    <td className="px-6 py-4">
                                        {value.quantity}
                                    </td>
                                    <td className="px-6 py-4">
                                        {`Rs. ${value.price}`}
                                    </td>
                                    <td className="px-6 py-4">
                                        {/* <button onClick={()=>handleEdit(value.id)} className="font-medium text-blue-600 hover:underline px-2">Edit</button> */}
                                      <Link className="font-medium text-blue-600 hover:underline px-2" to={`/editproduct/${value.id}`}>Edit</Link>
                                        <button onClick={() => handleDelete(value.id)} className="font-medium text-red-600  hover:underline px-2">Delete</button>
                                    </td>
                                </tr>
                            )
                        })}

                    </tbody>
                </table>
            </div>
            <div className="flex items-center justify-center space-x-4 m-4">
                <button type="button" className="text-white flex items-center border border-gray-600 bg-green-600 hover:bg-primary-800 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center" onClick={() => { navigate('/addProduct') }}>
                    Add Product
                </button>
            </div>
        </>
    );
}

export default Inventory
