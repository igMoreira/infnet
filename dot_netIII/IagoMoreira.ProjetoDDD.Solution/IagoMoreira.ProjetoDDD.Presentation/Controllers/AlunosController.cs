using IagoMoreira.ProjetoDDD.Application.Interfaces;
using IagoMoreira.ProjetoDDD.Application.ViewModel;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
//using IagoMoreira.ProjetoDDD.Domain;
//using IagoMoreira.ProjetoDDD.Infra.Data.Context;
//using IagoMoreira.ProjetoDDD.Infra.Data.Repositories;
//using IagoMoreira.ProjetoDDD.Infra.Data.Repositories.ReadOnly;

namespace IagoMoreira.ProjetoDDD.Presentation.Controllers
{
    public class AlunosController : Controller
    {
        //private readonly AlunoRepository repository = new AlunoRepository();
        private readonly IAlunoAppService appService;

        public AlunosController(IAlunoAppService _service)
        {
            appService = _service;
        }

        // GET: Alunos
        public ActionResult Index()
        {
            return View(appService.ObterAlunos());
        }

        // GET: Alunos/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            AlunoViewModel aluno = appService.ObterAlunoPorId(id.Value);
            if (aluno == null)
            {
                return HttpNotFound();
            }
            return View(aluno);
        }

        // GET: Alunos/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Alunos/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "AlunoId,Matricula,Nome,DataNascimento,DataCriação,CPF")] AlunoViewModel aluno)
        {
            if (ModelState.IsValid)
            {
                aluno.DataCriação = DateTime.Now;
                aluno.Matricula = Guid.NewGuid();
                appService.AdicionarAluno(aluno);
                return RedirectToAction("Index");
            }

            return View(aluno);
        }

        // GET: Alunos/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            AlunoViewModel aluno = appService.ObterAlunoPorId(id.Value);
            if (aluno == null)
            {
                return HttpNotFound();
            }
            return View(aluno);
        }

        // POST: Alunos/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "AlunoId,Matricula,Nome,DataNascimento,DataCriação,CPF")] AlunoViewModel aluno)
        {
            if (ModelState.IsValid)
            {
                appService.EditarAluno(aluno);
                return RedirectToAction("Index");
            }
            return View(aluno);
        }

        // GET: Alunos/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            AlunoViewModel aluno = appService.ObterAlunoPorId(id.Value);
            if (aluno == null)
            {
                return HttpNotFound();
            }
            return View(aluno);
        }

        // POST: Alunos/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            AlunoViewModel aluno = appService.ObterAlunoPorId(id);
            appService.DeletarAluno(aluno);
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            base.Dispose(disposing);
        }
    }
}
