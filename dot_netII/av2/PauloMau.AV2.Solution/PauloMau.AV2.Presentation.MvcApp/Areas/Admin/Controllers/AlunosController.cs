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
    public class AlunosController : Controller
    {
        private ContextoDb db = new ContextoDb();

        // GET: Admin/Alunos
        public ActionResult Index(string currentFilter, string searchString, int? page)
        {
            if(searchString != null)
            {
                page = 1;
            }
            else
            {
                searchString = currentFilter;
            }
            ViewBag.CurrentFilter = searchString;

            var alunos = from a in db.Alunos
                         select a;
            if (!String.IsNullOrEmpty(searchString))
            {
                alunos = alunos.Where(a => a.Nome.Contains(searchString)
                                   || a.CPF.Contains(searchString)
                                   || a.Email.Contains(searchString));
            }
            alunos = alunos.OrderBy(a => a.AlunoId);

            int pageSize = 30;
            int pageNumber = (page ?? 1);
            return View(alunos.ToPagedList(pageNumber, pageSize));
        }

        // GET: Admin/Alunos/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Aluno aluno = db.Alunos.Include( a => a.Endereco).FirstOrDefault(a => a.AlunoId == id);
            if (aluno == null)
            {
                return HttpNotFound();
            }
            return View(aluno);
        }

        // GET: Admin/Alunos/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Admin/Alunos/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Nome,CPF,Email,Nascimento,Cadastro,Mae,Endereco")] Aluno aluno)
        {
            if (ModelState.IsValid)
            {
                aluno.Cadastro = DateTime.Now;
                db.Alunos.Add(aluno);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(aluno);
        }

        // GET: Admin/Alunos/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Aluno aluno = db.Alunos.Include(a => a.Endereco).FirstOrDefault(a => a.AlunoId == id);
            if (aluno == null)
            {
                return HttpNotFound();
            }
            return View(aluno);
        }

        // POST: Admin/Alunos/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "AlunoId,Cadastro,Matricula,Nome,CPF,Email,Nascimento,Mae,Endereco")] Aluno aluno)
        {
            if (ModelState.IsValid)
            {
                db.Entry(aluno).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(aluno);
        }

        // GET: Admin/Alunos/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Aluno aluno = db.Alunos.Find(id);
            if (aluno == null)
            {
                return HttpNotFound();
            }
            return View(aluno);
        }

        // POST: Admin/Alunos/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Aluno aluno = db.Alunos.Find(id);
            db.Alunos.Remove(aluno);
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
