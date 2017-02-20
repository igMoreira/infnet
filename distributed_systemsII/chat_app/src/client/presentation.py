_MENU_HEADER = """
-----------------------------------
 Please select the desired command
-----------------------------------

"""

def show_logged_user_menu():
    """
        The logged user menu options.
    :return:
    """
    print(_MENU_HEADER)
    print("1. Login")
    print("2. Send")
    print("3. Logout")

def show_unlogged_user_menu():
    """
        The unlogged user menu options.
    :return:
    """
    print(_MENU_HEADER)
    print("1. Login")