package vn.tuanne.jobhunter.controller;

import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import vn.tuanne.jobhunter.domain.Company;
import vn.tuanne.jobhunter.domain.response.ResultPaginationDTO;
import vn.tuanne.jobhunter.service.CompanyService;
import vn.tuanne.jobhunter.util.annotation.ApiMessage;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/companies")
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company postmanCompany) {
        Company company = this.companyService.handleCreateCompany(postmanCompany);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

    @GetMapping("/companies")
    @ApiMessage("Fetch companys")
    public ResponseEntity<ResultPaginationDTO> getCompany(
            @Filter Specification<Company> spec, Pageable pageable) {

        return ResponseEntity.ok(this.companyService.handleGetCompany(spec, pageable));
    }

    @PutMapping("/companies")
    public ResponseEntity<Company> updateUser(@Valid @RequestBody Company reqCompany) {
        Company company = this.companyService.handleUpdateCompany(reqCompany);
        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") long id) {
        this.companyService.handleDeleteCompany(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/companies/{id}")
    @ApiMessage("Fetch company by id")
    public ResponseEntity<Company> fetchCompanyById(@PathVariable("id") long id) {
        Optional<Company> company = this.companyService.findById(id);
        return ResponseEntity.ok().body(company.get());
    }
}
