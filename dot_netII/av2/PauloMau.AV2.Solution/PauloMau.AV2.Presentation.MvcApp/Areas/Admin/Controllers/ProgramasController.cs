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
    public class ProgramasController : Controller
    {
        private ContextoDb db = new ContextoDb();

        // GET: Admin/Programas
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

            var programas = from p in db.Programa
                         select p;
            if (!String.IsNullOrEmpty(searchString))
            {
                programas = programas.Where(p => p.Nome.Contains(searchString)
                                   || p.Descricao.Contains(searchString));
            }
            programas = programas.OrderBy(p => p.ProgramaId);

            int pageSize = 30;
            int pageNumber = (page ?? 1);
            return View(programas.ToPagedList(pageNumber, pageSize));
        }

        // GET: Admin/Programas/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Programa programa = db.Programa.Include( p => p.Cursos).FirstOrDefault(p => p.ProgramaId == id);
            if (programa == null)
            {
                return HttpNotFound();
            }
            return View(programa);
        }

        // GET: Admin/Programas/Create
        public ActionResult Create()
        {
            var cursos = from c in db.Cursos
                         where c.Programa == null
                         select c;
            List<SelectListItem> cursoList = new List<SelectListItem>();
            foreach(var item in cursos.ToList())
            {
                cursoList.Add(new SelectListItem { Text = item.Nome, Value=item.CursoId.ToString()});
            }
            ViewBag.Cursos = cursoList;
            return View();
        }

        // POST: Admin/Programas/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "ProgramaId,Nome,Descricao,Cursos")] Programa programa)
        {
            if (ModelState.IsValid)
            {
                db.Programa.Add(programa);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(programa);
        }

        // GET: Admin/Programas/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Programa programa = db.Programa.Find(id);
            if (programa == null)
            {
                return HttpNotFound();
            }
            return View(programa);
        }

        // POST: Admin/Programas/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "ProgramaId,Nome,Descricao")] Programa programa)
        {
            if (ModelState.IsValid)
            {
                db.Entry(programa).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(programa);
        }

        // GET: Admin/Programas/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Programa programa = db.Programa.Find(id);
            if (programa == null)
            {
                return HttpNotFound();
            }
            return View(programa);
        }

        // POST: Admin/Programas/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Programa programa = db.Programa.Find(id);
            db.Programa.Remove(programa);
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
