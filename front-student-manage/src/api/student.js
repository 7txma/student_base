// src/api/student.js
import axios from 'axios'

// 创建 axios 实例
const request = axios.create({
  baseURL: '/api', // 会被 vite 代理转发到 http://localhost:8081
  timeout: 5000
})

// 请求拦截器
request.interceptors.request.use(
  config => config,
  error => Promise.reject(error)
)

// 响应拦截器
request.interceptors.response.use(
  response => response.data,
  error => {
    console.error('请求出错:', error)
    return Promise.reject(error)
  }
)

// 学生相关接口
export function getStudentList() {
  return request({
    url: '/students',
    method: 'get'
  })
}

export function addStudent(data) {
  return request({
    url: '/students',
    method: 'post',
    data
  })
}

export function updateStudent(id, data) {
  return request({
    url: `/students/${id}`,
    method: 'put',
    data
  })
}

export function deleteStudent(id) {
  return request({
    url: `/students/${id}`,
    method: 'delete'
  })
}