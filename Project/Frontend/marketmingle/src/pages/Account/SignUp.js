import React, { useState } from "react";
import { BsCheckCircleFill } from "react-icons/bs";
import { Link, useNavigate } from "react-router-dom";
import { logoLight } from "../../assets/images";
import Header from "../../components/home/Header/Header";
import { registerUser } from "../../Services/RegistrationService";

const SignUp = () => {
  const navigate = useNavigate();
  // ============= Initial State Start here =============
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [password, setPassword] = useState("");
  const [address, setAddress] = useState("");
  const [city, setCity] = useState("");
  const [country, setCountry] = useState("");
  const [zip, setZip] = useState("");
  const [checked, setChecked] = useState(false);
  const [user, setUser] = useState("");
  // ============= Initial State End here ===============
  // ============= Error Msg Start here =================
  const [errFirstName, setErrFirstName] = useState("");
  const [errLastName, setErrLastName] = useState("");
  const [errEmail, setErrEmail] = useState("");
  const [errPhone, setErrPhone] = useState("");
  const [errPassword, setErrPassword] = useState("");
  const [errAddress, setErrAddress] = useState("");
  const [errCity, setErrCity] = useState("");
  const [errCountry, setErrCountry] = useState("");
  const [errZip, setErrZip] = useState("");
  // ============= Error Msg End here ===================
  const [successMsg, setSuccessMsg] = useState("");
  // ============= Event Handler Start here =============
  const handleFirstName = (e) => {
    setFirstName(e.target.value);
    setErrFirstName("");
  };
  const handleLastName = (e) => {
    setLastName(e.target.value);
    setErrLastName("");
  };
  const handleEmail = (e) => {
    setEmail(e.target.value);
    setErrEmail("");
  };
  const handlePhone = (e) => {
    setPhone(e.target.value);
    setErrPhone("");
  };
  const handlePassword = (e) => {
    setPassword(e.target.value);
    setErrPassword("");
  };
  const handleAddress = (e) => {
    setAddress(e.target.value);
    setErrAddress("");
  };
  const handleCity = (e) => {
    setCity(e.target.value);
    setErrCity("");
  };
  const handleCountry = (e) => {
    setCountry(e.target.value);
    setErrCountry("");
  };
  const handleZip = (e) => {
    setZip(e.target.value);
    setErrZip("");
  };
  const handleUser = (e) => {
    setUser(e.target.value);
    
  };
  // ============= Event Handler End here ===============
  // ================= Email Validation start here =============
  const EmailValidation = (email) => {
    return String(email)
      .toLowerCase()
      .match(/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i);
  };
  // ================= Email Validation End here ===============

  // const handleSignUp = (e) => {
  //   e.preventDefault();
  //   if (checked) {
  //     if (!firstName) {
  //       setErrFirstName("Enter your name");
  //     }
  //     if (!lastName) {
  //       setErrLastName("Enter your name");
  //     }
  //     if (!email) {
  //       setErrEmail("Enter your email");
  //     } else {
  //       if (!EmailValidation(email)) {
  //         setErrEmail("Enter a Valid email");
  //       }
  //     }
  //     if (!phone) {
  //       setErrPhone("Enter your phone number");
  //     }
  //     if (!password) {
  //       setErrPassword("Create a password");
  //     } else {
  //       if (password.length < 6) {
  //         setErrPassword("Passwords must be at least 6 characters");
  //       }
  //     }
  //     if (!address) {
  //       setErrAddress("Enter your address");
  //     }
  //     if (!city) {
  //       setErrCity("Enter your city name");
  //     }
  //     if (!country) {
  //       setErrCountry("Enter the country you are residing");
  //     }
  //     if (!zip) {
  //       setErrZip("Enter the zip code of your area");
  //     }
  //     // ============== Getting the value ==============
  //     if (
  //       firstName &&
  //       lastName&&
  //       email &&
  //       EmailValidation(email) &&
  //       password &&
  //       password.length >= 6 &&
  //       address &&
  //       city &&
  //       country &&
  //       zip
  //     ) {
  //       setSuccessMsg(
  //         `Hello dear ${firstName}, Welcome you to MarketMingle Admin panel. We received your Sign up request. We are processing to validate your access. Till then stay connected and additional assistance will be sent to you by your mail at ${email}`
  //       );
  //       setFirstName("");
  //       setLastName("");
  //       setEmail("");
  //       setPhone("");
  //       setPassword("");
  //       setAddress("");
  //       setCity("");
  //       setCountry("");
  //       setZip("");
  //     }
  //   }
  // };
  //=======================handle Submit
  const handleSubmit = async (event) => {
    event.preventDefault();

    const requestData = {
      firstName: firstName,
      lastName: lastName,
      email: email,
      contact: phone,
      street_address: address,
      city: city,
      country: country,
      zip_code: zip,
      password: password,
      role:user
    };

    try {
      const response = await registerUser(requestData);
      console.log(requestData);
      if (response.data === 1) {
        alert("Registration Successful, You can LogIn now.");
        navigate("/signin");
      } else if (response.data === 0) {
        alert("Something went wrong");
        navigate("/signup");
      }
    } catch (error) {
      console.log(error);
    }finally{
      navigate("/signin")
    }
  };

  return (
    <>
      <Header />
      <div className="w-full h-screen flex items-center justify-start">
        <div className="w-1/3 hidden lgl:inline-flex h-full text-white">
          {/*  */}
        </div>
        <div className="w-full lgl:w-[500px] h-full flex flex-col justify-center">
          {successMsg ? (
            <div className="w-[500px]">
              <Link to="/signin">
                <button
                  className="w-full h-10 bg-primeColor rounded-md text-gray-200 text-base font-titleFont font-semibold 
            tracking-wide hover:bg-black hover:text-white duration-300"
                >
                  Sign in
                </button>
              </Link>
            </div>
          ) : (
            <form className="w-full lgl:w-[500px] h-screen flex items-center justify-center" onSubmit={handleSubmit}>
              <div className="px-6 py-4 w-full h-[96%] flex flex-col justify-start overflow-y-scroll scrollbar-thin scrollbar-thumb-primeColor">
                <h1 className="font-titleFont underline underline-offset-4 decoration-[1px] font-semibold text-2xl mdl:text-3xl mb-4">
                  Create your account
                </h1>
                <div className="flex flex-col gap-3">
                  {/* first name */}
                  <div className="flex flex-col gap-.5">
                    <p className="font-titleFont text-base font-semibold text-gray-600">
                      First Name
                    </p>
                    <input
                      onChange={handleFirstName}
                      value={firstName}
                      className="w-full h-8 placeholder:text-sm placeholder:tracking-wide px-4 text-base font-medium placeholder:font-normal rounded-md border-[1px] border-gray-400 outline-none"
                      type="text"
                      placeholder="eg. John Doe"
                    />
                    {errFirstName && (
                      <p className="text-sm text-red-500 font-titleFont font-semibold px-4">
                        <span className="font-bold italic mr-1">!</span>
                        {errFirstName}
                      </p>
                    )}
                  </div>
                  {/* last name */}
                  <div className="flex flex-col gap-.5">
                    <p className="font-titleFont text-base font-semibold text-gray-600">
                      Last Name
                    </p>
                    <input
                      onChange={handleLastName}
                      value={lastName}
                      className="w-full h-8 placeholder:text-sm placeholder:tracking-wide px-4 text-base font-medium placeholder:font-normal rounded-md border-[1px] border-gray-400 outline-none"
                      type="text"
                      placeholder="eg. John Doe"
                    />
                    {errLastName && (
                      <p className="text-sm text-red-500 font-titleFont font-semibold px-4">
                        <span className="font-bold italic mr-1">!</span>
                        {errLastName}
                      </p>
                    )}
                  </div>
                  {/* Email */}
                  <div className="flex flex-col gap-.5">
                    <p className="font-titleFont text-base font-semibold text-gray-600">
                      Work Email
                    </p>
                    <input
                      onChange={handleEmail}
                      value={email}
                      className="w-full h-8 placeholder:text-sm placeholder:tracking-wide px-4 text-base font-medium placeholder:font-normal rounded-md border-[1px] border-gray-400 outline-none"
                      type="email"
                      placeholder="john@workemail.com"
                    />
                    {errEmail && (
                      <p className="text-sm text-red-500 font-titleFont font-semibold px-4">
                        <span className="font-bold italic mr-1">!</span>
                        {errEmail}
                      </p>
                    )}
                  </div>
                  {/* Phone Number */}
                  <div className="flex flex-col gap-.5">
                    <p className="font-titleFont text-base font-semibold text-gray-600">
                      Phone Number
                    </p>
                    <input
                      onChange={handlePhone}
                      value={phone}
                      className="w-full h-8 placeholder:text-sm placeholder:tracking-wide px-4 text-base font-medium placeholder:font-normal rounded-md border-[1px] border-gray-400 outline-none"
                      type="text"
                      placeholder="008801234567891"
                    />
                    {errPhone && (
                      <p className="text-sm text-red-500 font-titleFont font-semibold px-4">
                        <span className="font-bold italic mr-1">!</span>
                        {errPhone}
                      </p>
                    )}
                  </div>
                  {/* Password */}
                  <div className="flex flex-col gap-.5">
                    <p className="font-titleFont text-base font-semibold text-gray-600">
                      Password
                    </p>
                    <input
                      onChange={handlePassword}
                      value={password}
                      className="w-full h-8 placeholder:text-sm placeholder:tracking-wide px-4 text-base font-medium placeholder:font-normal rounded-md border-[1px] border-gray-400 outline-none"
                      type="password"
                      placeholder="Create password"
                    />
                    {errPassword && (
                      <p className="text-sm text-red-500 font-titleFont font-semibold px-4">
                        <span className="font-bold italic mr-1">!</span>
                        {errPassword}
                      </p>
                    )}
                  </div>
                  {/* Address */}
                  <div className="flex flex-col gap-.5">
                    <p className="font-titleFont text-base font-semibold text-gray-600">
                      Address
                    </p>
                    <input
                      onChange={handleAddress}
                      value={address}
                      className="w-full h-8 placeholder:text-sm placeholder:tracking-wide px-4 text-base font-medium placeholder:font-normal rounded-md border-[1px] border-gray-400 outline-none"
                      type="text"
                      placeholder="road-001, house-115, example area"
                    />
                    {errAddress && (
                      <p className="text-sm text-red-500 font-titleFont font-semibold px-4">
                        <span className="font-bold italic mr-1">!</span>
                        {errAddress}
                      </p>
                    )}
                  </div>
                  {/* City */}
                  <div className="flex flex-col gap-.5">
                    <p className="font-titleFont text-base font-semibold text-gray-600">
                      City
                    </p>
                    <input
                      onChange={handleCity}
                      value={city}
                      className="w-full h-8 placeholder:text-sm placeholder:tracking-wide px-4 text-base font-medium placeholder:font-normal rounded-md border-[1px] border-gray-400 outline-none"
                      type="text"
                      placeholder="Your city"
                    />
                    {errCity && (
                      <p className="text-sm text-red-500 font-titleFont font-semibold px-4">
                        <span className="font-bold italic mr-1">!</span>
                        {errCity}
                      </p>
                    )}
                  </div>
                  {/* Country */}
                  <div className="flex flex-col gap-.5">
                    <p className="font-titleFont text-base font-semibold text-gray-600">
                      Country
                    </p>
                    <input
                      onChange={handleCountry}
                      value={country}
                      className="w-full h-8 placeholder:text-sm placeholder:tracking-wide px-4 text-base font-medium placeholder:font-normal rounded-md border-[1px] border-gray-400 outline-none"
                      type="text"
                      placeholder="Your country"
                    />
                    {errCountry && (
                      <p className="text-sm text-red-500 font-titleFont font-semibold px-4">
                        <span className="font-bold italic mr-1">!</span>
                        {errCountry}
                      </p>
                    )}
                  </div>
                  {/* Zip code */}
                  <div className="flex flex-col gap-.5">
                    <p className="font-titleFont text-base font-semibold text-gray-600">
                      Zip/Postal code
                    </p>
                    <input
                      onChange={handleZip}
                      value={zip}
                      className="w-full h-8 placeholder:text-sm placeholder:tracking-wide px-4 text-base font-medium placeholder:font-normal rounded-md border-[1px] border-gray-400 outline-none"
                      type="text"
                      placeholder="Your country"
                    />
                    {errZip && (
                      <p className="text-sm text-red-500 font-titleFont font-semibold px-4">
                        <span className="font-bold italic mr-1">!</span>
                        {errZip}
                      </p>
                    )}
                  </div>

                  
                  <div class="flex items-center mb-4">
                    <input id="default-radio-1" type="radio" value="SELLER" onChange={handleUser} name="User" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"/>
                      <label for="default-radio-1" class="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Seller</label>
                  </div>
                  <div class="flex items-center mb-4">
                    <input id="default-radio-1" type="radio" value="CUSTOMER" onChange={handleUser} name="User" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"/>
                      <label for="default-radio-1" class="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Customer</label>
                  </div>


                  {/* Checkbox */}
                  <div className="flex items-start mdl:items-center gap-2">
                    <input
                      onChange={() => setChecked(!checked)}
                      className="w-4 h-4 mt-1 mdl:mt-0 cursor-pointer"
                      type="checkbox"
                    />
                    <p className="text-sm text-primeColor">
                      I agree to the MarketMingle {" "}
                      <span className="text-blue-500">Terms of Service </span>and{" "}
                      <span className="text-blue-500">Privacy Policy</span>.
                    </p>
                  </div>
                  <button
                    onClick={handleSubmit}
                    className={`${checked
                      ? "bg-primeColor hover:bg-black hover:text-white cursor-pointer"
                      : "bg-gray-500 hover:bg-gray-500 hover:text-gray-200 cursor-none"
                      } w-full text-gray-200 text-base font-medium h-10 rounded-md hover:text-white duration-300`}
                  >
                    Create Account
                  </button >
                  <p className="text-sm text-center font-titleFont font-medium">
                    Don't have an Account?{" "}
                    <Link to="/signin">
                      <span className="hover:text-blue-600 duration-300">
                        Sign in
                      </span>
                    </Link>
                  </p>
                </div>
              </div>
            </form>
          )}
        </div>
      </div>
    </>
  );
};

export default SignUp;
