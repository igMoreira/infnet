using Infnet.GEC5.AV1.Iago.Moreira.Domain;
using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace Infnet.GEC5.AV1.Iago.Moreira
{
    public partial class ClientForm : Form
    {
        private List<Client> clientList = new List<Client>();

        public ClientForm()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void CleanBtn_Click(object sender, EventArgs e)
        {
            clearForm();
        }

        private void SaveBtn_Click(object sender, EventArgs e)
        {
            if(validateInput())
            {
                Client c = new Client
                {
                    ClientId = clientList.Count,
                    Name = NomeTxt.Text,
                    Address = EnderecoTxt.Text,
                    CPF = CPFTxt.Text,
                    Phone = TelefoneTxt.Text
                };
                clientList.Add(c);
                MessageBox.Show("Cliente salvo com sucesso!!");
                clearForm();
            }
        }

        private bool validateInput()
        {
            bool isInvalid = false;
            if (isInvalid = (NomeTxt.Text == ""))
                MessageBox.Show("O nome do cliente não pode ser vazio");
            if (isInvalid = (EnderecoTxt.Text == ""))
                MessageBox.Show("O endereço do cliente não pode ser vazio");
            if (isInvalid = (TelefoneTxt.Text == ""))
                MessageBox.Show("O telefone do cliente não pode ser vazio");
            if (isInvalid = (CPFTxt.Text == ""))
                MessageBox.Show("O CPF do cliente não pode ser vazio");
            return !isInvalid;
        }

        private void clearForm()
        {
            NomeTxt.Clear();
            EnderecoTxt.Clear();
            CPFTxt.Clear();
            TelefoneTxt.Clear();
        }

        private void ShowAllBtn_Click(object sender, EventArgs e)
        {
            foreach(Client c in clientList)
                MessageBox.Show(c.Name + " - " + c.CPF + " - " + c.Address + " - " + c.Phone);
        }

        private void BuscarBtn_Click(object sender, EventArgs e)
        {
            bool found = false;
            foreach(Client c in clientList)
            {
                if (c.Name.Contains(NomeSearchTxt.Text))
                {
                    found = true;
                    MessageBox.Show(c.Name + " - " + c.CPF + " - " + c.Address + " - " + c.Phone);
                }
            }
            if(!found)
            {
                MessageBox.Show("Nenhum cliente com este nome foi encontrado.");
            }
        }
    }
}
