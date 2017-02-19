using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using PauloMau.AV2.Domain.Context;
using PauloMau.AV2.Domain.Entities;
using PagedList;

namespace PauloMau.AV2.Presentation.MvcApp.Areas.Admin.Controllers
{
    public class ProfessoresController : Controller
    {
        private ContextoDb db = new ContextoDb();

        // GET: Admin/Professores
        public ActionResult Index(string currentFilter, string searchString, int? page)
        {
            if (searchString != null)
            {
                page = 1;
            }
            else
            {
                searchString = currentFilter;
            }
            ViewBag.CurrentFilter = searchString;

            var professores = from a in db.Professores
                         select a;
            if (!String.IsNullOrEmpty(searchString))
            {
                professores = professores.Where(a => a.Nome.Contains(searchString)
                                   || a.CPF.Contains(searchString)
                                   || a.Email.Contains(searchString));
            }
            professores = professores.OrderBy(p => p.ProfessorId);

            int pageSize = 30;
            int pageNumber = (page ?? 1);
            return View(professores.ToPagedList(pageNumber, pageSize));

            return View(db.Professores.ToList());
        }

        // GET: Admin/Professores/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Professor professor = db.Professores.Include(p => p.Endereco).FirstOrDefault(p => p.ProfessorId == id);
            if (professor == null)
            {
                return HttpNotFound();
            }
            return View(professor);
        }

        // GET: Admin/Professores/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Admin/Professores/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "ProfessorId,Matricula,Nome,CPF,Email,Nascimento,Cadastro,Endereco")] Professor professor)
        {
            if (ModelState.IsValid)
            {
                professor.Cadastro = DateTime.Now;
                db.Professores.Add(professor);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(professor);
        }

        // GET: Admin/Professores/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Professor professor = db.Professores.Include(p => p.Endereco).FirstOrDefault(p => p.ProfessorId == id);
            if (professor == null)
            {
                return HttpNotFound();
            }
            return View(professor);
        }

        // POST: Admin/Professores/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "ProfessorId,Matricula,Nome,CPF,Email,Nascimento,Cadastro,Endereco")] Professor professor)
        {
            if (ModelState.IsValid)
            {
                db.Entry(professor).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(professor);
        }

        // GET: Admin/Professores/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Professor professor = db.Professores.Find(id);
            if (professor == null)
            {
                return HttpNotFound();
            }
            return View(professor);
        }

        // POST: Admin/Professores/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Professor professor = db.Professores.Find(id);
            db.Professores.Remove(professor);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
