import React, { useEffect } from 'react'
import { useState } from 'react';
import userApi from '../../api/userApi';
import axios from 'axios';

function UserAdd() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [addr,setAddress] = useState([]);
    const [password,setPassword] = useState("");

    const [selectedAddr,setSelectedAddr] = useState("");
    const [term,setTerm] = useState([]);

    useEffect(() => {
        const fetchProvince = async () => {
            try {
                let res = await axios.get(
                    "https://provinces.open-api.vn/api/"
                );
                setAddress(res.data);
                setTerm(res.data);
            } catch (error) {
                console.log(error);
            }
        }
        fetchProvince();
    },[])

    const handleAddUser = async (name, email, phone,address,password) => {

        try {
            console.log("parent : ", name, email, phone,address,password)
            let newUser = {
                name,
                email,
                phone,
                address,
                password
            };
            setName("")
            setEmail("")
            setPhone("")
            setPassword("")
            //Gọi API createUser phía server
            let res = await userApi.createUser(newUser);
        } catch (e) {
            alert(e.response.data.message);
        }
    };

   




    return (
        <div className="container mt-5 mb-5">
            <h2 className="text-center text-uppercase mb-3">Tạo user</h2>

            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="bg-light p-4">
                        <div className="mb-3">
                            <label className="col-form-label">Name</label>
                            <input type="text" id="name" className="form-control"
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                            />
                        </div>
                        <div className="mb-3">
                            <label className="col-form-label">Email</label>
                            <input type="text" id="email" className="form-control"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                            />
                        </div>
                        <div className="mb-3">
                            <label className="col-form-label">Phone</label>
                            <input type="text" id="phone" className="form-control"
                                value={phone}
                                onChange={(e) => setPhone(e.target.value)}
                            />
                        </div>
                        <div className="mb-3">
                            <label className="col-form-label">Address</label>
                            <select className="form-select" id="address" 
                            onChange = {(e)=>{
                                setSelectedAddr(e.target.value);
                            }
                            }

                            >
                                <option hidden >Chọn Địa Chỉ</option>
                                {
                                    addr.map((p)=>(
                                        <option value = {p.name} key ={p.code}>
                                            {p.name}
                                        </option>
                                    ))
                                }
                            </select>
                        </div>
                        <div className="mb-3">
                            <label className="col-form-label" >Password</label>
                            <input type="text" id="password" className="form-control" value = {password} onChange={(e)=>setPassword(e.target.value)}/>
                        </div>
                    </div>
                    <div className="text-center mt-3">
                        <button className="btn btn-secondary btn-back">Quay lại</button>
                        <button className="btn btn-success" id="btn-save" onClick={(e)=>handleAddUser(name,email,phone,selectedAddr,password)}>Tạo User</button>
                    </div>
                </div>
            </div>
        </div>


    )
}

export default UserAdd