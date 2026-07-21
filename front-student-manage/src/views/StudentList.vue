<template>
  <a-card title="学生管理">
    <template #extra>
      <a-button type="primary" @click="handleAdd">新增学生</a-button>
    </template>

    <a-table
      :columns="columns"
      :data-source="students"
      :loading="loading"
      row-key="studentId"
    >
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <a @click="handleEdit(record)">编辑</a>
          <a-popconfirm title="确定删除？" @confirm="handleDelete(record.studentId)">
            <a style="margin-left: 12px; color: red;">删除</a>
          </a-popconfirm>
        </template>
      </template>
    </a-table>

    <a-modal
      v-model:open="visible"
      :title="isEdit ? '编辑学生' : '新增学生'"
      @ok="handleOk"
    >
      <a-form :model="form">
        <a-form-item label="学号">
          <a-input v-model:value="form.studentId" />
        </a-form-item>
        <a-form-item label="姓名">
          <a-input v-model:value="form.name" />
        </a-form-item>
        <a-form-item label="班级">
          <a-input v-model:value="form.className" />
        </a-form-item>
      </a-form>
    </a-modal>
  </a-card>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { message } from 'ant-design-vue'
import axios from 'axios'

const loading = ref(false)
const students = ref([])
const visible = ref(false)
const isEdit = ref(false)

const form = ref({
  studentId: '',
  name: '',
  className: ''
})

const columns = [
  { title: '学号', dataIndex: 'studentId', key: 'studentId' },
  { title: '姓名', dataIndex: 'name', key: 'name' },
  { title: '班级', dataIndex: 'className', key: 'className' },
  { title: '操作', key: 'action' }
]

const loadData = async () => {
  loading.value = true
  try {
    const res = await axios.get('/api/students')
    students.value = res.data
  } catch (err) {
    console.error('加载失败:', err)
    message.error('加载失败')
  } finally {
    loading.value = false
  }
}

const handleAdd = () => {
  isEdit.value = false
  form.value = { studentId: '', name: '', className: '' }
  visible.value = true
}

const handleEdit = (record) => {
  isEdit.value = true
  form.value = { ...record }
  visible.value = true
}

const handleDelete = async (id) => {
  try {
    await axios.delete(`/api/students/${id}`)
    message.success('删除成功')
    loadData()
  } catch (err) {
    console.error('删除失败:', err)
    message.error('删除失败')
  }
}

// 直接用 axios 原生方式发请求
const handleOk = async () => {
  try {
    if (isEdit.value) {
      await axios.put(`/api/students/${form.value.studentId}`, form.value)
      message.success('修改成功')
    } else {
      await axios.post('/api/students', form.value)
      message.success('新增成功')
    }
    visible.value = false
    loadData()
  } catch (err) {
    console.error('请求错误详情:', err)
    // 打印后端返回的错误信息
    if (err.response) {
      console.log('后端返回状态:', err.response.status)
      console.log('后端返回数据:', err.response.data)
    }
    message.error('操作失败，请看控制台错误')
  }
}

onMounted(() => {
  loadData()
})
</script>