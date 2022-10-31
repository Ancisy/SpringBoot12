import axios from 'axios';
import React from 'react'
import { useState, useEffect } from 'react';
import userApi from "../../api/userApi"
import UserAdd from './UserAdd';

function UserEdit() {

    const [users, setUser] = useState([]);
    const [term, setTerm] = useState("");

    const [SearchUser, setSearchUser] = useState([]);

    useEffect(() => {
        const fetchusers = async () => {
            try {
                let res = await axios.get(
                    "http://localhost:8080/api/v1/user"
                );
                console.log(res)
                setUser(res.data);
                setSearchUser(res.data);
            } catch (error) {
                console.log(error);
            }
        };
        fetchusers();
    }, []);


    useEffect(() => {
        handleSearch();
    }, [users])


    const handleDeleteUser = async (id) => {
        try {
            await userApi.deleteUser(id);
            const newUser = users.filter((user) => user.id !== id);
            setUser(newUser);
            setSearchUser(newUser);
        } catch (e) {
            console.log(e);
        }
    }

    const handleSearch = () => {
        if (!term) {
            setSearchUser(users);
            return;
        }
        const SearchUser = users.filter(
            (user) => user.name.toLowerCase().includes(term.toLowerCase())
        );
        console.log(SearchUser)
        setSearchUser(SearchUser);
        return SearchUser;
    }


    return (
        <div className="container mt-5 mb-5">
             

            <h2 className="text-center text-uppercase">Danh sách user</h2>

            <div className="row justify-content-center">
                <div className="col-md-10">

                    <div className="d-flex justify-content-between align-items-center mt-5 mb-4">
                        <a href="/add" className="btn btn-warning" >Tạo user</a>
                        <input type="text" id="search" className="form-control w-50" placeholder="Tìm kiếm user" onChange={(e) => {
                            setTerm(e.currentTarget.value);
                        }} />
                        <button onClick={handleSearch}>Search</button>
                    </div>

                    <div className="bg-light p-4">
                        <table className="table table-hover">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Address</th>
                                    <th></th>
                                </tr>
                            </thead>

                            <tbody>

                                {
                                    SearchUser.map((user) => (
                                        <tr key={user.id}>
                                            <td>{user.id}</td>
                                            <td>{user.name}</td>
                                            <td>{user.email}</td>
                                            <td>{user.phone}</td>
                                            <td>{user.address}</td>
                                            <td>
                                                <a href={users.avatar} className="btn btn-success">Xem chi tiết</a>
                                                <button className="btn btn-danger" onClick={() => handleDeleteUser(user.id)}>Xóa</button>
                                            </td>
                                        </tr>
                                    ))
                                }
                            </tbody>
                        </table>
                        <p className="message d-none"></p>
                    </div>
                </div>

                
            </div>

         
        </div>

        
    )
}

export default UserEdit