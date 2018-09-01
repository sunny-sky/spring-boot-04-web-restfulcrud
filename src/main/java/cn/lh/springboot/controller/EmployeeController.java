package cn.lh.springboot.controller;

import cn.lh.springboot.dao.DepartmentDao;
import cn.lh.springboot.dao.EmployeeDao;
import cn.lh.springboot.entities.Department;
import cn.lh.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    /**
     * 查询所有员工
     * @return 所有用户列表页面
     */
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);

        //thymeleaf默认拼接字符串  classpath:/templates/***.html
        return "emp/list";
    }

    /**
     * 转跳增加员工页面
     * @return 增加员工页面
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        // 查出部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);

        return "emp/add";
    }

    /**
     * 增加员工
     * @return 重定向所有用户列表页面
     */
    @PostMapping("emp")
    public String addEmp(Employee employee){

        System.out.println("保存的员工的信息"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
}
