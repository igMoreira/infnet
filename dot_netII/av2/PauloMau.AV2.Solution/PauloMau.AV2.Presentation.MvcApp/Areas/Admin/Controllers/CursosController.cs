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
    public class CursosController : Controller
    {
        private ContextoDb db = new ContextoDb();

        // GET: Admin/Cursos
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

            var cursos = from c in db.Cursos
                         select c;
            if (!String.IsNullOrEmpty(searchString))
            {
                cursos = cursos.Where(c => c.Nome.Contains(searchString)
                                   || c.Codigo.Contains(searchString)
                                   || c.Descricao.Contains(searchString)
                                   || c.Objetivos.Contains(searchString));
            }
            cursos = cursos.OrderBy(c => c.CursoId);

            int pageSize = 30;
            int pageNumber = (page ?? 1);
            return View(cursos.ToPagedList(pageNumber, pageSize));
        }

        // GET: Admin/Cursos/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Curso curso = db.Cursos.Find(id);
            if (curso == null)
            {
                return HttpNotFound();
            }
            return View(curso);
        }

        // GET: Admin/Cursos/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Admin/Cursos/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Nome,Objetivos,Descricao")] Curso curso)
        {
            if (ModelState.IsValid)
            {
                curso.Codigo = Guid.NewGuid().ToString("N").Substring(0, 10);
                db.Cursos.Add(curso);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(curso);
        }

        // GET: Admin/Cursos/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Curso curso = db.Cursos.Include(c => c.Programa).FirstOrDefault(c => c.CursoId == id);
            if (curso == null)
            {
                return HttpNotFound();
            }
            return View(curso);
        }

        // POST: Admin/Cursos/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "CursoId,Nome,Codigo,Objetivos,Descricao,Programa")] Curso curso)
        {
            if (ModelState.IsValid)
            {
                db.Entry(curso).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(curso);
        }

        // GET: Admin/Cursos/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Curso curso = db.Cursos.Include(c => c.Programa).FirstOrDefault(c => c.CursoId == id);
            if (curso == null)
            {
                return HttpNotFound();
            }
            return View(curso);
        }

        // POST: Admin/Cursos/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Curso curso = db.Cursos.Find(id);
            db.Cursos.Remove(curso);
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
