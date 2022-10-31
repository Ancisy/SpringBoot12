import React from 'react'
import { useState, useEffect } from 'react';

function UserList() {
   

  return (
    <div class="container mt-5 mb-5">
    <h2 class="text-center text-uppercase mb-3">Thông tin user</h2>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="bg-light p-4">
                <div class="mb-3">
                    <label class="col-form-label">Name</label>
                    <input type="text" id="name" class="form-control" />
                </div>
                <div class="mb-3">
                    <label class="col-form-label">Email</label>
                    <input type="text" id="email" class="form-control" />
                </div>
                <div class="mb-3">
                    <label class="col-form-label">Phone</label>
                    <input type="text" id="phone" class="form-control" />
                </div>
                <div class="mb-3">
                    <label class="col-form-label">Address</label>
                    <select class="form-select" id="address">
                        
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label">Avatar</label>
                    <div class="avatar-preview mb-3 rounded">
                        <img src="" alt="avatar" id="avatar-preview" class="rounded"/>
                    </div>

                    <label for="avatar" class="btn btn-warning">Đổi avatar</label>
                    <input type="file" id="avatar" class="d-none"/>
                  </div>
                <div class="mb-3">
                    <label class="col-form-label">Password</label>
                    <div class="">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#modal-change-password">
                            Đổi mật khẩu
                        </button>
                        <button class="btn btn-warning" id="btn-forgot-password">
                            Quên mật khẩu
                        </button>
                    </div>
                </div>
            </div>
            <div class="text-center mt-3">
                <button class="btn btn-secondary btn-back">Quay lại</button>
                <button class="btn btn-success" id="btn-save">Cập nhật</button>
            </div>
        </div>
    </div>

    {/* <!-- Modal đổi mật khẩu--> */}
    <div class="modal fade" id="modal-change-password" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
        aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Đổi mật khẩu</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label class="col-form-label">Mật khẩu cũ</label>
                        <input type="text" id="old-password" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <label class="col-form-label">Mật khẩu mới</label>
                        <input type="text" id="new-password" class="form-control" />
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button type="button" class="btn btn-primary" id="btn-change-password">Xác nhận</button>
                </div>
            </div>
        </div>
    </div>
</div>
  )
}

export default UserList