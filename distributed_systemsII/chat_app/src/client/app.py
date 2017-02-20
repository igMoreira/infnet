import manager
import presentation as ui

host = ''
port = 8080

__CLIENT_MANAGER = manager.ClientManager(host, port)

while True:
    if __CLIENT_MANAGER.is_logged():
        ui.show_logged_user_menu()

    else:
        ui.show_unlogged_user_menu()